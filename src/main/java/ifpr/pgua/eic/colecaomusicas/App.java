package ifpr.pgua.eic.colecaomusicas;

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

        }
}
