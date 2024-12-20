import java.io.File;
import java.io.FileWriter;

public class CreateBuild
{

    static FileWriter build;
    static File dir;

    public static void main(String[] args) throws Exception
    {
        String dir_str = System.getProperty("user.dir");

        File build_file = new File(dir_str + "/copy/build.txt");
        if (build_file.exists())
            build_file.createNewFile();
        build = new FileWriter(build_file);

        dir = new File(dir_str + "/build/");

        copyFileAsDirectory(dir);

        build.close();
    }

    public static void copyFileAsClass(File in) throws Exception
    {
        String path = in.getAbsolutePath();
        String dir_path = dir.getAbsolutePath();

        String new_path = path.replace(dir_path + "\\", "");
        
        build.write(new_path + "\n");
    }

    

    static void copyFileAsDirectory(File in) throws Exception
    {
        File[] f_list = in.listFiles();

        // Get the name and separate the "src"

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
                    if (n_and_e[1].equals("class"))
                    {
                        copyFileAsClass(f_list[i]);
                    }
                }
            }
        }
    }
}
