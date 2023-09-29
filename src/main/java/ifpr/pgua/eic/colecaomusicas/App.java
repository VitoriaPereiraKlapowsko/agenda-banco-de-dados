package ifpr.pgua.eic.colecaomusicas;

import ifpr.pgua.eic.colecaomusicas.controllers.BucarAgenda;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroAgenda;
import ifpr.pgua.eic.colecaomusicas.controllers.Principal;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

public class App extends BaseAppNavigator {

        public static void main(String[] args) {
                launch();
        }

        @Override
        public String getHome() {
                return "PRINCIPAL";
        }

        @Override
        public String getAppTitle() {
                return "Agenda";
        }

        @Override
        public void registrarTelas() {
                registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o -> new Principal()));
                registraTela("CADASTROAGENDA",
                                new ScreenRegistryFXML(App.class, "cadastro.fxml", o -> new CadastroAgenda()));
                registraTela("BUSCARAGENDA",
                                new ScreenRegistryFXML(App.class, "busca.fxml", o -> new BucarAgenda()));
        }

}
