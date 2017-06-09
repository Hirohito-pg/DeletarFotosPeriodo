import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DeletarFotos {
    File arquivo;
    String caminho;

    public DeletarFotos() throws FileNotFoundException {
        VerificarTXT();
    }

    public static void main(String[] args) throws FileNotFoundException {
        DeletarFotos deletarFotos = new DeletarFotos();
    }

    public void pegarCaminho() {
        JOptionPane.showMessageDialog(null, "Selecione o Caminho!");
        JFileChooser jFileChooser_seletor = new JFileChooser();
        jFileChooser_seletor.setFileSelectionMode(1);
        int retorno = jFileChooser_seletor.showOpenDialog(null);
        if (retorno == 0) {
            try {
                this.caminho = jFileChooser_seletor.getSelectedFile().getAbsolutePath();
                escreverTXT();
                apagarFotos();
            } catch (Exception e) {
                this.caminho = "";
                JOptionPane.showMessageDialog(null, "Erro ao Selecionar o Caminho!", "Erro", 0);
                System.exit(0);
            }
        } else {
            this.caminho = "";
            JOptionPane.showMessageDialog(null, "Erro ao Selecionar o Caminho!", "Erro", 0);
            System.exit(0);
        }
    }

    public void escreverTXT() {
        String textoQueSeraEscrito = this.caminho;
        try {
            FileWriter arquivoEscrito = new FileWriter(new File("Não_Apague_O_Arquivo.txt"));
            arquivoEscrito.write(textoQueSeraEscrito);
            arquivoEscrito.close();
        } catch (IOException e) {
        }
    }

    public void lerTXT() {
        try {
            FileReader reader = new FileReader("Não_Apague_O_Arquivo.txt");
            Throwable localThrowable3 = null;
            try {
                BufferedReader leitor = new BufferedReader(reader);
                Throwable localThrowable4 = null;
                try {
                    String linha = leitor.readLine();
                    if (linha != null) {
                        this.caminho = linha;
                    }
                    this.arquivo = new File("Não_Apague_O_Arquivo.txt");
                } catch (Throwable localThrowable1) {
                    localThrowable4 = localThrowable1;
                    throw localThrowable1;
                } finally {
                }
            } catch (Throwable localThrowable2) {
                localThrowable3 = localThrowable2;
                throw localThrowable2;
            } finally {
                if (reader != null) if (localThrowable3 != null) try {
                    reader.close();
                } catch (Throwable x2) {
                    localThrowable3.addSuppressed(x2);
                }
                else reader.close();
            }
        } catch (IOException e) {
        }
    }

    public void VerificarTXT() throws FileNotFoundException {
        try {
            lerTXT();
            File caminhoArquivo = new File(this.caminho);
            if (this.arquivo.isFile()) {
                if (caminhoArquivo.exists()) apagarFotos();
                else pegarCaminho();
            } else pegarCaminho();
        } catch (Exception e) {
            pegarCaminho();
        }
    }

    public void apagarFotos() {
        File file = new File(this.caminho);
        String formato = ".jpg";
        File[] TodosArquivos = file.listFiles();
        int i = 0;
        for (int j = TodosArquivos.length; i < j; i++) {
            if ((TodosArquivos[i].getName().length() > 4) && (TodosArquivos[i].getName().substring(TodosArquivos[i].getName().length() - 4, TodosArquivos[i].getName().length()).equals(formato))) {
                File data = new File(this.caminho + "\\" + TodosArquivos[i].getName());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date dt = new Date(data.lastModified());
                Calendar cal = Calendar.getInstance();
                long dr = cal.getTimeInMillis() - dt.getTime();
                Long aux = Long.valueOf(dr / 86400000L);
                Integer dias = Integer.valueOf(aux.intValue());
                if (dias.intValue() >= 5) {
                    File deletar = new File(this.caminho + "\\" + TodosArquivos[i].getName());
                    deletar.delete();
                }
            }
        }
        System.exit(0);
    }
}