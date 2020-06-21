package site.dunhanson.email.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.*;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import site.dunhanson.email.entity.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author dunhanson
 * 2020-06-21
 * Email工具类
 */
@Slf4j
public class EmailUtils {

    /**
     * 发送文本
     * @param smtp
     * @param authenticator
     * @param content
     * @return 发送结果
     */
    public static Result sendText(Smtp smtp, Authenticator authenticator, Content content) {
        Result result;
        try {
            Email email = new SimpleEmail();
            //设置smtp
            setSmtp(email, smtp);
            //设置认证
            setAuthenticator(email, authenticator);
            //设置内容
            setContent(email, content);
            //发送邮件
            String message = email.send();
            //返回结果
            result = new Result(true, message);
        } catch (EmailException e) {
            result = handleException(e.getCause());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = handleException(e.getCause());
        }
        return result;
    }

    /**
     * 发送文本
     * @param sender
     * @param receiver
     * @param content
     * @return 发送结果
     */
    public static Result sendText(Sender sender, Receiver receiver, Content content) {
        EmailConfigUtils.configContent(sender, receiver, content);
        // 发送结果
        return EmailUtils.sendText(sender.getSmtp(), sender.getAuthenticator(), content);
    }

    /**
     * 发送文本
     * @param sender
     * @param receiver
     * @param subject
     * @param msg
     * @return
     */
    public static Result sendText(Sender sender, Receiver receiver, String subject, String msg) {
        Content content = Content.builder()
                .subject(subject)
                .msg(msg)
                .build();
        return EmailUtils.sendText(sender, receiver, content);
    }

    /**
     * 发送文本
     * @param transceiver
     * @param subject
     * @param msg
     * @return
     */
    public static Result sendText(Transceiver transceiver, String subject, String msg) {
        FromTo fromTo = EmailConfigUtils.getFromTo(transceiver);
        return EmailUtils.sendText(fromTo.getFrom(), fromTo.getTo(), subject, msg);
    }



    /**
     * 发送文件
     * @param smtp
     * @param authenticator
     * @param content
     * @param attachment
     * @return 发送结果
     */
    public static Result sendAttachment(Smtp smtp, Authenticator authenticator, Content content, Attachment attachment) {
        Result result;
        try {
            //attachment
            EmailAttachment emailAttachment = new EmailAttachment();
            String pathOrUlr = attachment.getPathOrUrl();
            if(pathOrUlr.startsWith("http")) {
                emailAttachment.setURL(new URL(pathOrUlr));
            } else {
                emailAttachment.setPath(pathOrUlr);
            }
            emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(attachment.getDescription());
            attachment.setName(attachment.getName());
            //email
            MultiPartEmail email = new MultiPartEmail();
            //设置smtp
            setSmtp(email, smtp);
            //设置认证
            setAuthenticator(email, authenticator);
            //设置内容
            setContent(email, content);
            //设置附件
            email.attach(emailAttachment);
            //发送邮件
            String message = email.send();
            //返回结果
            result = new Result(true, message);
        } catch (EmailException | MalformedURLException e) {
            result = handleException(e.getCause());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = handleException(e.getCause());
        }
        return result;
    }

    /**
     * 发送文件
     * @param sender
     * @param receiver
     * @param content
     * @param attachment
     * @return
     */
    public static Result sendAttachment(Sender sender, Receiver receiver, Content content, Attachment attachment) {
        EmailConfigUtils.configContent(sender, receiver, content);
        return sendAttachment(sender.getSmtp(), sender.getAuthenticator(), content, attachment);
    }

    /**
     * 发送邮件内容
     * @param smtp
     * @param authenticator
     * @param content
     * @return 发送结果
     */
    public static Result sendHTML(Smtp smtp, Authenticator authenticator, Content content, String url) {
        Result result;
        try {
            //email
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(new URL(url)));
            //设置smtp
            setSmtp(email, smtp);
            //设置认证
            setAuthenticator(email, authenticator);
            //设置内容
            setContent(email, content);
            //设置HTML
            email.setHtmlMsg(content.getMsg());
            //设置替换内容
            email.setTextMsg("Your email client does not support HTML messages");
            //发送邮件
            String message = email.send();
            //返回结果
            result = new Result(true, message);
        } catch (EmailException e) {
            result = handleException(e.getCause());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = handleException(e.getCause());
        }
        return result;
    }

    /**
     * 发送邮件内容
     * @param sender
     * @param receiver
     * @param content
     * @param url
     * @return
     */
    public static Result sendHTML(Sender sender, Receiver receiver, Content content, String url) {
        EmailConfigUtils.configContent(sender, receiver, content);
        return sendHTML(sender.getSmtp(), sender.getAuthenticator(), content, url);
    }

    /**
     * 设置smtp
     * @param email
     * @param smtp
     */
    public static void setSmtp(Email email, Smtp smtp) {
        email.setSmtpPort(smtp.getPort());
        email.setHostName(smtp.getHost());
        email.setSSLOnConnect(smtp.getSsl());
    }

    /**
     * 设置认证
     * @param email
     * @param authenticator
     */
    public static void setAuthenticator(Email email, Authenticator authenticator) {
        email.setAuthenticator(new DefaultAuthenticator(authenticator.getUsername(), authenticator.getPassword()));
    }

    /**
     * 设置内容
     * @param email
     * @param content
     */
    public static void setContent(Email email, Content content) {
        try {
            email.addTo(content.getTo(), content.getToName());
            email.setFrom(content.getFrom(), content.getFromName());
            email.setSubject(content.getSubject());
            email.setMsg(content.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理异常信息
     * @param throwable
     * @return 发送结果
     */
    public static Result handleException(Throwable throwable) {
        String clazz = throwable.getClass().getName();
        String error = throwable.getMessage();
        return new Result(false, clazz + ";" + error);
    }
}
