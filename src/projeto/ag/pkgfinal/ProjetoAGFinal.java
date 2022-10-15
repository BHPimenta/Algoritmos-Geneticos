
package projeto.ag.pkgfinal;
import java.util.Random;
/**
 *
 * @author Bruno
 */
public class ProjetoAGFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int k=10;
        int[] vet = new int[k];
        int cand=20;
        int[][] Candidatos = new int[cand][k];
        int matrizPesos[][] = new int[k][k];
        CriacaoMatrizPesos(matrizPesos, k);
        for(int i=0; i<k; i++){
            for(int j=0; j<k; j++){
                System.out.print(matrizPesos[i][j]+" " );
            }
            System.out.println();
        }
        System.out.println();
        VetBinario(vet, k);
        /*for(int i=0; i<k; i++){
            System.out.print(vet[i]);
        }*/
        PrimeiraColuna(Candidatos, cand);
                
        PrimeiraLinha(Candidatos,k, vet);
        
        ConstrucaoCromossomos(Candidatos, k, vet, cand);
        
        Imprime(Candidatos, k, cand);
        int[] vetfitness = new int[cand];
        
        ConstrucaoVetorFitness(vetfitness,matrizPesos,cand, k, Candidatos );
    } 
    
    public static void CriacaoMatrizPesos(int[][] matrizPesos, int k){
        Random gerador = new Random();
        
        for(int i=0; i<k; i++){
            for(int j=i+1; j<k; j++){
                int x = gerador.nextInt(100)+1;
                matrizPesos[i][j] = x;
                matrizPesos[j][i] = x;
            }
        }
          
    }
    
    public static void VetBinario(int[] vet, int k){    
        for(int i=0; i<k; i++){
            for(int j=0; j<k; j++){
                if(j==0){//ponto de partido de todos os cromossos é em 0
                    vet[j]=1;
                }
                else{
                    vet[j]=0;
                }   
            }
        }
    }
    public static void PrimeiraColuna(int[][] Candidatos, int cand){
        for(int i=0;i<cand;i++){
            Candidatos[i][0] = 0; 
        }
    }
    public static void PrimeiraLinha(int[][] Candidatos, int k, int[]vet){
        int aux=1, j=1;
        while(aux<=k-1){
            int valor = new Random().nextInt(k-1);
            valor=valor+1;
            if(vet[valor]==0){
                Candidatos[0][j]=valor;
                vet[valor]=1;
                aux++;
                j++;
            }
        }
    }
    
    public static void ConstrucaoCromossomos(int[][] Candidatos, int k, int[] vet, int cand){
        int aux;
        for(int i=1;i<cand;i++){
            VetBinario(vet, k);
            for(int j=1;j<k;j++){
                aux=1;
                while(aux==1){
                    if(i+1<=j){
                    Candidatos[i][j]=Candidatos[i-1][j-1];
                    vet[Candidatos[i][j]]=1;
                    aux=0;
                    }
                    else{
                        int valor = new Random().nextInt(k-1);
                        valor=valor+1;
                        if(vet[valor]==0){
                        Candidatos[i][j]=valor;
                        vet[valor]=1;
                        aux=0;
                        }  
                    }    
                }
            }   
        }    
    }
    
    public static void Imprime(int[][] Candidatos, int k, int cand){
        for(int i=0; i<cand;i++){
            for(int j=0; j<k; j++){
                System.out.print(Candidatos[i][j]+"\t");
            }
            System.out.print("\n");
        }
    }
    
    public static void ConstrucaoVetorFitness(int []vetfitness, int[][] matrizPesos,int cand, int k, int[][] Candidatos ){
        int soma=0;
        for(int i=0; i<cand; i++){
            soma=0;
            int j=0;
            int p1=0, p2=0;
            System.out.println();
            while(j+1<k){
                p1=Candidatos[i][j];
                p2=Candidatos[i][j+1];
                soma=soma+matrizPesos[p1][p2];
                j++;
                
                System.out.print(p1+"\t");
                /*System.out.print(p2+"\t");*/
                /*System.out.print(p1+" "+p2+"\t");*/
                /*System.out.print("soma "+soma+"\t");*/
            }
            if(j+1>=k){
                p1=Candidatos[i][j];
                p2=Candidatos[i][0];
                System.out.print(p1+"\t");
                System.out.print(p2+"\t");
                soma=soma+matrizPesos[p1][p2];
                /*System.out.print("soma "+soma+"\t");*/
                /*System.out.print(p1+" "+p2+"\t");
                System.out.print("soma "+soma+"\t");*/
            }
            System.out.print(soma+"\t");
            vetfitness[i]=soma;
        }
        /*System.out.println("vetor fitness");
        for(int i=0; i<cand; i++){
            System.out.print(vetfitness[i]+"\t");
        }*/    
    }    
}
    
   
    

    
        

