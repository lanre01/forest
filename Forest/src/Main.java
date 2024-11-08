//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    Controller controller = new Controller();
    View view = new View();
    Model model = new Model();

    public Main() {
        this.model.initialise(view, controller);
        System.out.println("controller done");
        this.controller.initialise(this.model, this.view);
        System.out.println("controller done");
        this.view.initialise(this.model, this.controller);
        System.out.println("controller done");
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        new Main();
    }
}
