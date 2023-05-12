package org.example.common.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static org.example.common.utils.DataBuffer.*;
import static org.example.common.utils.ResponseCode.WRONG_CONNECTION;

@Getter
@Setter
public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = -8504915906614662334L;

    public RequestType type;
    public Object content;

    public Request(RequestType type, Object content) {
        this.type = type;
        this.content = content;
    }

    public Response request() throws IOException {
        try {
//            //发送请求
//            String message = gson.toJson(this);
//
//            if(!message.endsWith("\n")) {
//                message += "\n";
//            }
//
//            pw.print(message);
//            pw.flush();

            //使用writeObject发送
            Request request = new Request(
                    this.type,
                    this.content
            );

            System.out.println("content:"+this.content);

            oos.writeObject(request);
            oos.flush();
            System.out.println("message:" + this.type);

            //读取response
            Response response = (Response) ois.readObject();

//            String temp = ois.readLine();
//            StringBuilder response = new StringBuilder();
//            while(temp != null){
//                response.append(temp);
//                temp = ois.readLine();
//            }

            //TODO 处理response
            System.out.println("response:" + response);

            //response的json解析
            return response;

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //请求失败
        return new Response(
                WRONG_CONNECTION,
                "wrong connection",
                null
        );
    }
}
