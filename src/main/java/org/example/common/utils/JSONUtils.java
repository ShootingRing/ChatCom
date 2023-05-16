package org.example.common.utils;

import org.apache.commons.io.FileUtils;
import org.example.common.entities.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class JSONUtils {
    public static String ReadJsonFile(String address) throws IOException {
        address = "/" + address;
        //读取Json文件
        File json = new File(Objects.requireNonNull(User.class.getResource(address)).getFile());

        System.out.println(address + " has been read");

        return FileUtils.readFileToString(json, "utf-8");
    }

    public static void WriteToJsonFile(String content, String address) throws IOException {
        address = "src/main/resources/" + address;
        System.out.println("ready to write " + address);
        File file = new File(address);
        //如果文件不存在，报错
        if (!file.exists()){
            System.err.println("file not exists!");
            return;
        }

        //创建FileWriter对象
        FileWriter writer = new FileWriter(file);
        //向文件中写入内容
        writer.write(content);
        writer.flush();
        writer.close();
    }
}
