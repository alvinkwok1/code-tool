package cn.alvinkwok.codetool.network;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description
 *
 * @author alvinkwok
 * @since 2024/5/24
 */
public class Test {


    public static void main(String[] args) {
        String bin = "19f10400a10000009d0104f10100a810de8355c3f45c894e2a26c3feb61c7697776e69250b59a6708056a896f0db8c66501de20000006f010000006a000f01000000630100093a80000000000048000caebe738bacf2f71addb01bf90048c951553690fb0cce81e785f87a5fe7f637047ace52663b16adf61e9843d64c4e433cf17cdcac49293286ef09ed92f8aadd903dcfa426817a3708766943b7b0a1e1612396bf2e830319f10400248b617ed2e5b80edca98cf35439420cf17963517254e0aa6bfbab68e8e565d49cb4f27d4d17f1040166b61c16b2d0160b5f90a2c0aa5e9e0e599db99b51d01fef5fcbdec1fbf37be0aeba159f3f8c053296b931737a7d32138d9af9118329ef5a0795eddd222c143767d76dbc361645cc10c5f44d970b5faa7d6f79fefd51b5ad15ad9b8e10ec1beba551b9555eb3aa6ba1038d34440aa457da3c55e3b279652880516aea06e5aecc9d176c8f82f9dada725d3c1ac191aeb8a8981d68c1628a0dd8051fd8ac6337f97c593fbf6a623063688f2f24ce48ff992ab090b4ceca3a416f08545fe2c2e9543a2bc1c3b074d8db37b270ed0c948478ab8868bb0f59c6db87f2def05f0aba68a3846069d6dbf476fb8d72daffe07f53a78be7a02341a7e947cee72e8fac63066e9ac8a65f9b2330a00c0a853e7d6921b00a9d7bd77a4e71bd707b2220be18dae79c6100dfe36b75361a1151d3ca841cfc8c5c579330dbbc93c4b6f16f46059a27d34f8e2b815b026edaa12db8f45b0a15a1e98bf899b4607260aec790235c845c4a5bbf4b9f5115f104001779693c7bd80dfecdd082d966a2b7204c2f800ff5a34ff3";
        // bin是一个十六进制的字符串，转换为一个二进制后编码为ASCII码，写入到文件
        byte[] bytes = new byte[bin.length() / 2];
        for (int i = 0; i < bin.length(); i += 2) {
            String sub = bin.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(sub, 16);
        }
        try (FileOutputStream fos = new FileOutputStream("F:/test.bin")) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
