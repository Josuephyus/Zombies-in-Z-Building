
import java.io.File;
import java.io.FileWriter;

public class CreateSource
{

    static FileWriter source;

    public static void main(String[] args) throws Exception
    {
        String dir_str = System.getProperty("user.dir");


        File source_file = new File(dir_str + "/copy/source.txt");
        if (!source_file.exists())
            source_file.createNewFile();
        source = new FileWriter(source_file);

        File dir = new File(dir_str + "/src/");
        copyFileAsDirectory(dir);

        source.close();
    }

    static void copyFileAsJava(File in) throws Exception
    {
        String path = in.getAbsolutePath();

        source.write(path + "\n");
    }

    static void copyFileAsDirectory(File in) throws Exception
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
                        copyFileAsJava(f_list[i]);
                    }
                }
            }
        }
    }
}