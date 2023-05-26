package org.example.common.utils;

import org.example.common.entities.OnlineSocketStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Currency;
import java.util.List;

import static org.example.common.utils.DataBuffer.socketMap;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 8504915852014662338L;

    public String from; //消息发送方UUID
    public List<String> to; //消息接收方UUID列表
    public String content; //消息内容

    public void sendMessage(OnlineSocketStream currentSocket) throws IOException {
        Response back = new Response(
            ResponseCode.SEND_BACK,
                "receive message",
                content
        );

        Response res = new Response(
                ResponseCode.RECEIVE_MESSAGE,
                "receive message",
                this
        );

        //往发送方回传
        back.response(currentSocket);

        //给接收方传
        for(String uuid : to){
            res.response(socketMap.get(uuid));
        }
    }
}
