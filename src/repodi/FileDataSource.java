package repodi;

import java.io.*;
import java.util.List;
import java.util.UUID;
//todo; utilizar DI no filename e deixar o tratamento da exceção para quem chamar
public class FileDataSource {
    public void writeToFile(List<String> data) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            //var outputStream= new FileOutputStream("/home/lt-sw-195/Área de Trabalho/db.csv");
            fileWriter = new FileWriter("/home/lt-sw-195/Área de Trabalho/db.csv", true);
            bufferedWriter = new BufferedWriter(fileWriter);

            //cria o ID do filme
            bufferedWriter.write(UUID.randomUUID() + ";");
            for (int i = 0; i < data.size(); i++) {
                if (i == data.size() - 1) {
                    bufferedWriter.write(data.get(i) + "\n");
                    break;
                }
                bufferedWriter.write(data.get(i) + ";");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("===============================");
            System.out.println("Ocorreu um problema na escrita do arquivo.");
        } finally {
            if (fileWriter != null && bufferedWriter != null) {
                try {
                    fileWriter.close();
                    bufferedWriter.close();
                } catch (IOException ioe) {
                    ioe.getStackTrace();
                }
            }
        }
    }

    public String[] searchInFile(String uuid) {
        FileReader fR = null;
        BufferedReader bR = null;
        String[] info = null;
        try {
            fR = new FileReader("/home/lt-sw-195/Área de Trabalho/db.csv");
            bR = new BufferedReader(fR);
            while (bR.ready()) {
                var line = bR.readLine().strip();
                if (!line.isEmpty() || line.isBlank()) {
                    info = line.split(";");
                    if (info[0].equals(uuid)) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("===============================");
            System.out.println("Ocorreu um problema na leitura do arquivo.");
            info = null;
        } finally {
            try {
                if (fR != null && bR != null) {
                    bR.close();
                    fR.close();
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
            return info;
        }
    }
}
