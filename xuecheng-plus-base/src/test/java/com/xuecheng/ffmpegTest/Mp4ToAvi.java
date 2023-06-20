package com.xuecheng.ffmpegTest;

import com.xuecheng.base.utils.Mp4VideoUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mp4ToAvi {
    public static void main(String[] args) throws IOException {
        //ffmpeg的路径
        String ffmpeg_path = "D:\\ffmpeg-6.0-essentials_build\\ffmpeg-6.0-essentials_build\\bin\\ffmpeg.exe";//ffmpeg的安装位置
        //源mp视频的路径
        String video_path = "E:\\family\\yd\\素材\\ce118aa358c20e6595748e1d998cf95c.mp4";

        //转换后mp4文件的路径
        String mp4_path = "C:\\Users\\qiu\\Desktop\\avi视频素材\\demo04.avi";
        String[] command = {ffmpeg_path, "-i", video_path, "-c:v", "copy", "-c:a", "copy", mp4_path};

        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
            System.out.println("转换完成！");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
