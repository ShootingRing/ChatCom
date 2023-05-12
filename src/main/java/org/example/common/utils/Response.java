package org.example.common.utils;

import org.example.common.entities.OnlineSocketStream;

import java.io.*;
import java.net.Socket;

import static org.example.common.utils.DataBuffer.gson;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = -8504915906614662334L;
    public ResponseCode code;
    public String message;
    public String data;

    public Response(ResponseCode code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = gson.toJson(data);
    }

    public void response(OnlineSocketStream currentSocket) {
        try{
            //返回response
//            String res = gson.toJson(this);
//
//            if(!res.endsWith("\n")) {
//                res += "\n";
//            }

            Response response = new Response(
                    this.code,
                    this.message,
                    this.data
            );

            currentSocket.getOos().writeObject(response);
            currentSocket.getOos().flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
