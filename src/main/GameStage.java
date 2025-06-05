package main;

public class GameStage {

	private int currentStage;
    private String[] dialogMessages;

    public GameStage() {
        this.currentStage = 0;
        dialogMessages = new String[] {
            "Bem-vindo ao jogo!",
            "Preparando para a próxima fase...",
            "Você está pronto para começar?"
        };
    }

    public void advanceStage() {
        if (currentStage < dialogMessages.length - 1) {
            currentStage++;
        } else {
            System.out.println("Iniciando a fase do jogo...");
        }
    }

    public String getCurrentDialog() {
        return dialogMessages[currentStage];
    }

    public int getCurrentStage() {
        return currentStage;
    }
}

