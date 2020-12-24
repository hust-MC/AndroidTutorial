package com.emercy.libsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        //1. Create ServerSocket
        ServerSocket serverSocket = new ServerSocket(8888);
        //2. monitoring
        System.out.println("server start listen : " + getIpAddress());

        Socket socket = serverSocket.accept();
        System.out.println("accept");

        //3. input stream
        InputStream is = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);
        String content;
        StringBuffer sb = new StringBuffer();
        while ((content = br.readLine()) != null) {
            sb.append(content);
        }

        System.out.println("server receiver: " + sb.toString());

        socket.shutdownInput();

        br.close();
        reader.close();
        is.close();

        socket.close();
        serverSocket.close();


        System.out.println("server receiver: ");

    }

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e.toString());
        }
        return "";
    }
}