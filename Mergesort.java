import java.io.*;

class Series_Filmes {
    private String nome;
    private String idioma;
    private String formato;
    private String duracao;
    private String pais;
    private String emissora;
    private String transmissao;
    private int temporadas;
    private int episodios;

    // Construtor vazio
    public Series_Filmes() {
        this.idioma = "";
        this.nome = "";
        this.duracao = "";
        this.formato = "";
        this.pais = "";
        this.emissora = "";
        this.transmissao = "";
        this.temporadas = 0;
        this.episodios = 0;
    }

    // Construtor que recebe parâmetros
    Series_Filmes(String nome, String idioma, String duracao, String formato, String pais, String emissora, String transmissao,
            int temporadas, int episodios) {
        this.nome = nome;
        this.idioma = idioma;
        this.duracao = duracao;
        this.formato = formato;
        this.pais = pais;
        this.emissora = emissora;
        this.transmissao = transmissao;
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    // encapsulamento das variáveis
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getFormato() {
        return this.formato;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDuracao() {
        return this.duracao;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return this.pais;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public String getEmissora() {
        return this.emissora;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public String getTransmissao() {
        return this.transmissao;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getTemporadas() {
        return this.temporadas;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }

    public int getEpisodios() {
        return this.episodios;
    }

    public void clone(Series_Filmes x) {
        this.nome = x.getNome();
        this.idioma = x.getIdioma();
        this.duracao = x.getDuracao();
        this.formato = x.getFormato();
        this.pais = x.getPais();
        this.emissora = x.getEmissora();
        this.transmissao = x.getTransmissao();
        this.temporadas = x.getTemporadas();
        this.episodios = x.getEpisodios();
    }

    public static String removetags(String s) {
        String resp = "";
        boolean dentroTag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '<') {
                // resp += s.charAt(i);
                dentroTag = false;
            } else if (s.charAt(i) == '>') {
                // resp += s.charAt(i);
                dentroTag = true;
            } else if (dentroTag == true) {
                resp += s.charAt(i);
            }
        }
        return resp;
    }

    public static int trataInt(String s) {
        int resp = 0;
        String aux = "";
        // MyIO.print(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
                    || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7'
                    || s.charAt(i) == '8' || s.charAt(i) == '9') {
                aux += s.charAt(i);
            }
            if (s.charAt(i) == ' ') {
                break;
            }
        }
        resp = Integer.parseInt(aux);

        return resp;
    }

    public static String consertaPais(String s) {
        String resp = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '&') {
                i += 6;
            }
            resp += s.charAt(i);
        }

        return resp;
    }

    public static String consertatransmissao(String s) {
        String resp = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '&') {
                i += 6;
            }
            resp += s.charAt(i);
        }

        return resp;
    }


    
    public String concertaNome(String fileName){
        String resp = "";
        for(int i = 0; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ 
                resp += ' ';
            } else { 
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); 
    }

    
    public void ler(String nome, String endereco) throws Exception {
        InputStreamReader irs = new InputStreamReader(new FileInputStream(endereco));

        BufferedReader br = new BufferedReader(irs);

        // leitura do nome
        while (!br.readLine().contains("<table class=\"infobox_v2\""))
            ;
        br.readLine();

        this.nome = concertaNome(nome);
        // leitura do formato

        while (!br.readLine().contains(">Formato<"))
            ;
        setFormato(removetags(br.readLine()));
        // leitura da duração
        while (!br.readLine().contains(">Dura"))
            ;
        setDuracao(removetags(br.readLine()).trim());
       // leitura do pais de origem
        while (!br.readLine().contains("s de origem<"))
            ;
        setPais(consertaPais(removetags(br.readLine())).trim());
        // leitura do idioma
        while (!br.readLine().contains(">Idioma"))
            ;
        setIdioma(removetags(br.readLine()).trim());
        // leitura da emissora de tv
        while (!br.readLine().contains(">Emissora de televis"))
            ;
        setEmissora(removetags(br.readLine()).trim());
       // leitura da transmissao
        while (!br.readLine().contains(">Transmiss"))
            ;
        setTransmissao(consertatransmissao(removetags(br.readLine())).trim());
        // leitura do n de temporadas
        while (!br.readLine().contains(" de temporadas<"))
            ;
        setTemporadas(trataInt(removetags(br.readLine())));
        // leitura do n de episodios
        while (!br.readLine().contains(" de epis"))
            ;
        setEpisodios(trataInt(removetags(br.readLine())));

        br.close();
    }

   
    public String imprimir() {
        return this.nome + " " + this.formato + " " + this.duracao + " " + this.pais + " " + this.idioma + " " + this.emissora + " " + this.transmissao + " " + this.temporadas + " " + this.episodios;

    }

}

public class Filmes {
    private Series_Filmes series[];
    private int n;
    public Filmes (int maxTam) { 
        this.series = new Series_Filmes[maxTam];
        this.n = 0;
    }


    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
 

    //insere no inicio
    void inserirInicio(Series_Filmes x) throws Exception {
        if (n >= series.length)
        throw new Exception("Erro!");
        for (int i = n; i > 0; i--){
        series[i] = series[i-1];
        }
        series[0] = x;
        n++;
        }

    void inserirFim(Series_Filmes x) throws Exception {
        if (n >= series.length)
        throw new Exception("Erro!");
        series[n] = x;
        n++;
    }
    
    void inserir(Series_Filmes x, int pos) throws Exception {
        if (n >= series.length || pos < 0 || pos > n)
        throw new Exception("Erro!");
        for (int i = n; i > pos; i--){
        series[i] = series[i-1];
        }
        series[pos] = x;
        n++;
        }

    Series_Filmes removerInicio() throws Exception {
        if (n == 0)
        throw new Exception("Erro!");
        Series_Filmes resp = series[0];
        n--;
        for (int i = 0; i < n; i++){
        series[i] = series[i+1];
        }
        return resp;
        }

    Series_Filmes removerFim() throws Exception {
        if (n == 0)
        throw new Exception("Erro!");
        return series[--n];
    }

    Series_Filmes remover(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos >= n)
        throw new Exception("Erro!");
        Series_Filmes resp = series[pos];
        n--;
        for (int i = pos; i < n; i++){
        series[i] = series[i+1];
        }
        return resp;
        }
        
        void swap(int x, int y){
            Series_Filmes tmp = series[x];
            series[x] = series[y];
            series[y] = tmp;
        }

    void mostrar (){
        
        for (int i = 0; i < n; i++){
            MyIO.println(series[i].imprimir());
        }
        
    } 

   // metodos de ordenação
   

   public void Merge_sort() {
    mergesort(0, n-1);
 }

 
 private void mergesort(int esq, int dir) {
    if (esq < dir){
       int meio = (esq + dir) / 2;
       mergesort(esq, meio);
       mergesort(meio + 1, dir);
       intercalar(esq, meio, dir);
    }
 }


 public void intercalar(int esq, int meio, int dir){
    int n1, n2, i, j, k;

    //Definir tamanho dos dois subarrays
    n1 = meio-esq+1;
    n2 = dir - meio;

    Series_Filmes[] a1 = new Series_Filmes[n1+1];
    Series_Filmes[] a2 = new Series_Filmes[n2+1];

    //Inicializar primeiro subarray
    for(i = 0; i < n1; i++){
       a1[i] = series[esq+i];
    }

    //Inicializar segundo subarray
    for(j = 0; j < n2; j++){
       a2[j] = series[meio+j+1];
    }

    //Sentinela no final dos dois arrays
    Series_Filmes sentinela = new Series_Filmes();
    sentinela.setEpisodios(0x7FFFFFFF);
    a1[i] = a2[j] = sentinela;
 
    //Intercalacao propriamente dita
    for(i = j = 0, k = esq; k <= dir; k++){
       series[k] = (a1[i].getEpisodios() <= a2[j].getEpisodios() || (a1[i].getEpisodios() == a2[j].getEpisodios() && a1[i].getNome().compareTo( a2[j].getNome()) <= 0)) ? a1[i++] : a2[j++];
    }
 }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        String[] entrada = new String[1000];
        int numEntrada = 0;
        Filmes list = new Filmes(70);
        Series_Filmes s;
        // String url = "";
        String url = "/tmp/series/";

        // Leitura do html
        do {
            entrada[numEntrada] = MyIO.readLine();
            String endereco = url + entrada[numEntrada];
            s = new Series_Filmes();
            if (isFim(entrada[numEntrada]) == false) {
                s.ler(entrada[numEntrada], endereco);
                list.inserirFim(s);
            }
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;

    // execução da ordenação na list e mostrando depois
        list.Merge_sort();
        list.mostrar();

    }
}
