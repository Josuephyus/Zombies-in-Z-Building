set "name=Main"
set "JDK=C:\Users\gamew\OneDrive\Documents\Code\Java\_DK"

set "dir=%cd%"
set "dir_src=%dir%\src"
set "dir_build=%dir%\build"
set "dir_copy=%dir%\copy"

rd "%dir_build%" /s /q
mkdir "%dir_build%"

set "java=%JDK%\bin\java.exe"
set "javac=%JDK%\bin\javac.exe"
set "jar=%JDK%\bin\jar.exe"



"%java%" "%dir_copy%\CopyRawFiles.java"
"%java%" "%dir_copy%\CreateSource.java"
"%javac%" -sourcepath "%dir_src%" -d "%dir_build%" @"%dir_copy%\source.txt"



"%java%" "%dir_copy%\CreateBuild.java"
cd "%dir_build%"
"%jar%" cfm "%dir%\%name%.jar" "%dir_copy%\manifest.txt" @"%dir_copy%\build.txt"

cd "%dir%"
"%java%" -jar "%name%.jar"