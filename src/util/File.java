package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class File
{
    public static String read(String path) throws IOException
    {
        return Files.readString(Paths.get(path));
    }

    public static void write(String content, String path) throws IOException
    {
        Files.write(Paths.get(path), content.getBytes());
    }
}
