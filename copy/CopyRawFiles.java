import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyRawFiles
{
    public static void main(String[] args) throws Exception
    {
        String dir_str = System.getProperty("user.dir");

        File dir = new File(dir_str + "/src/");

        copyFileAsDirectory(dir);
    }

    static void copyFileAsDirectory(File in)
    {
        File[] f_list = in.listFiles();

        // Get the name and separate the "src"
        String path = in.getAbsolutePath();
        String[] paths = path.split("src");
        String new_path = paths[0] + "build";
        if (paths.length > 1)
            new_path += paths[1];
        File new_folder = new File(new_path);
        new_folder.mkdir();


        for (int i = 0; i < f_list.length; i++)
        {
            if (f_list[i].isDirectory())
            {
                copyFileAsDirectory(f_list[i]);
            }
            else
            {
                String name = f_list[i].getName();
                String[] n_and_e = name.split("[.]");
                if (n_and_e.length > 1)
                {
                    if (n_and_e[1].equals("java"))
                    {
                        continue;
                    }
                    else
                    {
                        copyFileAsFile(f_list[i], new_folder);
                    }
                }
                else
                {
                    copyFileAsFile(f_list[i], new_folder);
                }
            }
        }
    }


    static void copyFileAsFile(File in, File folder)
    {
        String name = in.getName();
        File out = new File(folder.getAbsolutePath() + "\\" + name);
        try
        {
            FileInputStream in_r = new FileInputStream(in);
            FileOutputStream out_w = new FileOutputStream(out);

            int input = in_r.read();
            while (input != -1)
            {
                out_w.write(input);
                input = in_r.read();
            }
            out_w.close();
            in_r.close();

            FileInputStream out_r = new FileInputStream(out);
            in_r = new FileInputStream(in);

            int input1 = in_r.read();
            int input2 = out_r.read();
            while (input1 != -1 && input2 != -1)
            {
                if (input1 != input2)
                    System.out.println
                    (
                        Integer.toBinaryString(input1)
                        + " and " +
                        Integer.toBinaryString(input2)
                    );
                input1 = in_r.read();
                input2 = out_r.read();
            }

            out_r.close();
            in_r.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(name + " to " + folder.getAbsolutePath());
    }
}