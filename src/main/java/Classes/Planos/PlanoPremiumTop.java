package Classes.Planos;

import java.io.Serializable;

public class PlanoPremiumTop implements PlanoSubscricao, Serializable {

    @Override
    public int pontosPorMusica(int pontosAtuais, boolean jaOuviu){
        if (!jaOuviu) {
            return (int) (0.025 * pontosAtuais);
        }
        return 0;
    }

    public boolean podeCriarPlaylists(){
        return true;
    }

    public boolean podeAcederAFavoritos(){
        return true;
    }

    public String getNomePlano(){
        return "Plano PremiumTop";
    }
}
