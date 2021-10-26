package repodi;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*Usar Singleton quando uma classe for utilizada com frequência por várias partes distintas do sistema,
    e essa classe NÃO GERENCIA nenhum estado da aplicação;*/
public class PropertyUtils {

    /**
     * Instância Singleton.
     */
    private static PropertyUtils instance;

    /**
     * Atributo para recuperar as propriedades do sistema.
     */
    private final Properties props;

    private PropertyUtils(){
        try{
            props = new Properties();
            FileReader file = new FileReader("/home/lt-sw-195/Área de Trabalho/repodi.properties");
            props.load(file);
        }catch (IOException e){
            throw new RuntimeException("Erro ao obter as propriedades do sistema.");
        }
    }

    /**
     * Obtém a instância do Singleton.
     * @return instância do Singleton.
     */
    private static PropertyUtils getInstance(){
        if(instance ==null){
            instance = new PropertyUtils();
        }
        return instance;
    }

    /**
     * Obtém valor alfanumérico de uma propriedade do sistema.
     * @param property nome da propriedade para buscar valor no arquivo de properties.
     * @return valor alfanumérico da propriedade do sistema.
     */
    public static String getString(String property){
        return getInstance().props.getProperty(property);
    }

    /**
     * Obtém valor booleano de uma propriedade do sistema.
     * @param property nome da propriedade para buscar valor no arquivo de properties.
     * @return valor booleano da propriedade do sistema.
     */
    public static Boolean getBoolean(String property){
        return Boolean.parseBoolean(getString(property));
    }

    /**
     * Obtém valor inteiro de uma propriedade do sistema.
     * @param property nome da propriedade para buscar valor no arquivo de properties.
     * @return valor inteiro da propriedade do sistema.
     */
    public static Integer getInt(String property){
        return Integer.parseInt(getString(property));
    }

    /**
     * Obtém arquivo configurado numa propriedade do sistema.
     * @param property nome da propriedade para buscar valor no arquivo de properties.
     * @return arquivo configurado na propriedade do sistema.
     */
    public static File getFile(String property){
        return new File(getString(property));
    }
}
