import org.junit.Test;
import site.dunhanson.email.entity.*;
import site.dunhanson.email.utils.EmailConfigUtils;
import site.dunhanson.email.utils.EmailUtils;

public class Simple {
    @Test
    public void text() {
        // 发送者
        Sender sender = EmailConfigUtils.getSender(0);
        String from = sender.getName();
        Smtp smtp = sender.getSmtp();
        Authenticator authenticator = sender.getAuthenticator();
        // 接收者
        Receiver receiver = EmailConfigUtils.getReceiver(0);
        String to = receiver.getAddress();
        // 发送内容
        long timestamp = System.currentTimeMillis();
        String subject = "test-" + timestamp;
        String msg = "hello" + timestamp;
        Content content = Content.builder()
                .subject(subject)
                .msg(msg)
                .from(from)
                .to(to)
                .build();
        // 发送结果
        Result result = EmailUtils.sendText(smtp, authenticator, content);
        System.out.println(result);
    }

    @Test
    public void text1() {
        // 发送者
        Sender sender = EmailConfigUtils.getSender(0);
        // 接收者
        Receiver receiver = EmailConfigUtils.getReceiver(0);
        // 发送内容
        long timestamp = System.currentTimeMillis();
        String subject = "test-" + timestamp;
        String msg = "hello" + timestamp;
        Content content = Content.builder()
                .subject(subject)
                .msg(msg)
                .build();
        // 发送结果
        Result result = EmailUtils.sendText(sender, receiver, content);
        System.out.println(result);
    }

    @Test
    public void text2() {
        // 发送者
        Sender sender = EmailConfigUtils.getSender(0);
        // 接收者
        Receiver receiver = EmailConfigUtils.getReceiver(0);
        // 发送内容
        long timestamp = System.currentTimeMillis();
        String subject = "test-" + timestamp;
        String msg = "hello" + timestamp;
        // 发送结果
        Result result = EmailUtils.sendText(sender, receiver, subject, msg);
        System.out.println(result);
    }
}
