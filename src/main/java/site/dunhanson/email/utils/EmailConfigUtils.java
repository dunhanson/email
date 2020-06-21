package site.dunhanson.email.utils;

import site.dunhanson.email.entity.*;
import site.dunhanson.utils.basic.YamlUtils;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dunhanson
 * 2020-06-21
 * 邮件配置工具类
 */
public class EmailConfigUtils {
    /**配置信息**/
    public static String configPath = "email.yaml";
    /**邮件信息**/
    public static Email email = YamlUtils.load(configPath, Email.class, "email");
    /**发送者MAP**/
    public static Map<String, Sender> senderMap = email.getFrom()
            .stream().collect(Collectors.toMap(Sender::getName, sender -> sender));
    /**接收者MAP**/
    public static Map<String, Receiver> receiverMap = email.getTo()
            .stream().collect(Collectors.toMap(Receiver::getName, receiver -> receiver));


    /**
     * 获取FromTo
     * @param transceiver
     * @return
     */
    public static FromTo getFromTo(Transceiver transceiver) {
        Sender from = senderMap.get(transceiver.getFrom());
        Receiver to = receiverMap.get(transceiver.getTo());
        return new FromTo(from, to);
    }

    /**
     * 获取发送者
     * @param index
     * @return
     */
    public static Sender getSender(int index) {
        return email.getFrom().get(index);
    }

    /**
     * 获取接收者
     * @param index
     * @return
     */
    public static Receiver getReceiver(int index){
        return email.getTo().get(index);
    }

    /**
     * 配置Content
     * @param sender
     * @param receiver
     * @param content
     */
    public static void configContent(Sender sender, Receiver receiver, Content content) {
        // 发送者
        String from = sender.getAuthenticator().getUsername();
        String fromName = sender.getName();
        // 接收者
        String to = receiver.getAddress();
        String toName = receiver.getName();
        // 发送内容
        content.setFrom(from);
        content.setFromName(fromName);
        content.setTo(to);
        content.setToName(toName);
    }
}
