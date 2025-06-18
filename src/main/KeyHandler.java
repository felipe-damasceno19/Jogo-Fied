package main;

import java.awt.event.KeyEvent; // Representa eventos de tecla pressionada
import java.awt.event.KeyListener; // Interface para detectar entradas do teclado

// A classe KeyHandler implementa KeyListener para capturar eventos do teclado
public class KeyHandler implements KeyListener {
    
	GamePanel gp;
	
    // Variáveis booleanas para indicar quais teclas estão pressionadas
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, fPressed;
    
    //DEBUG
    public boolean showDebugText = false; 
    
    private long lastMoveTime = 0;  // Último tempo que a seleção foi movida
    private final long moveCooldown = 200;  // Tempo de espera (em milissegundos) entre as movimentações

    
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    	
    }
    
 // Método obrigatório da interface KeyListener, mas não será usado
    @Override
    public void keyTyped(KeyEvent e) {    
    }

    // Método chamado quando uma tecla é pressionada
    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // Obtém o código da tecla pressionada
        
        if (gp.gameState == gp.cutsceneState) {
        	cutsceneState(code);
        	return;
        }
        
        //TELA DE INICIO
        if(gp.gameState == gp.titleState) {
        	titleState(code);        	
        }
        //PLAY STATE
        if (gp.gameState == gp.playState) {
            playState(code);           
        }
        
        //PAUSE STATE
        else if (gp.gameState == gp.pauseState) {        	 
            pauseState(code); // ESC para pausar
          }

        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
        	dialogueState(code);
         }
        
        // CHARACTER STATE
        else if(gp.gameState == gp.characterState) {
        	characterState(code);
        }
        //OPTIONS STATE
        else if(gp.gameState == gp.optionsState) {
        	optionsState(code);
        }
        //GAMEOVER STATE
        else if(gp.gameState == gp.gameOverState) {
        	gameOverState(code);
        }
        else if(gp.gameState == gp.lockPickState) {
        	lockpickState(code);
        }
        else if(gp.gameState == gp.powerBoxState) {
        	powerBoxState(code);
        }
        else if (gp.gameState == gp.culpritSelectionState) {
            culpritSelectionState(code);
        }        
        else if (gp.gameState == gp.choiceState) {
            choiceState(code);
        }



        
     }
     public void titleState (int code) {
    	 if (code == KeyEvent.VK_W) {
             gp.ui.commandNum--;
             if(gp.ui.commandNum < 0) {
             	
             	gp.ui.commandNum = 2;
             }
          }
          if (code == KeyEvent.VK_S) {
         	 gp.ui.commandNum++;
         	 if(gp.ui.commandNum > 2) {
              	
              	gp.ui.commandNum = 0;
              }
          }
          if(code == KeyEvent.VK_ENTER) {
         	 
         	 if(gp.ui.commandNum == 0) {
         		 gp.gameState = gp.playState;
         		 gp.stopMusic();
         		 gp.playMusic(0);
         	 }
         	 
         	 if(gp.ui.commandNum == 1) {
         		 
         		 System.exit(0);
         		 
         	 }
          }
     }
     public void playState (int code) {
    	 if (code == KeyEvent.VK_W) {
             upPressed = true;
         }
         if (code == KeyEvent.VK_S) {
             downPressed = true;
         }
         if (code == KeyEvent.VK_A) {
             leftPressed = true;
         }
         if (code == KeyEvent.VK_D) {
             rightPressed = true;

         }
         if (code == KeyEvent.VK_F) {
             fPressed = true;

         }
         
         if (code == KeyEvent.VK_V) {
        	 

        	// gp.cutsceneManager.startCutscene("end2");
        	 System.out.println("Dialogos fechados:" + gp.closedDialogues);
        	 System.out.println("Estágio do Jogo:" + gp.gameStage.currentStage);
        	 System.out.println("Estágio do Jogo:" + gp.tasksComplete);
        	 
         }
         if (code == KeyEvent.VK_C) {
         	gp.gameState = gp.characterState;
         }

         // DEBUG
         if (code == KeyEvent.VK_T) {
         	showDebugText  = !showDebugText ;
         }

         if (code == KeyEvent.VK_R) {
         	switch(gp.currentMap) {
         	case 0: gp.tileM.loadMap("/maps/sampleM1.txt",0); break;
         	case 1: gp.tileM.loadMap("/maps/sampleS1.txt",1); break;
         	}
         }
         
         // ESC para pausar
         if (code == KeyEvent.VK_P) {
             gp.gameState = gp.pauseState;
         }
         if (code == KeyEvent.VK_ENTER) {
         	enterPressed = true;
         }
         if (code == KeyEvent.VK_ESCAPE) {
        	 gp.gameState = gp.optionsState;
         }
         
         if(code == KeyEvent.VK_M) {
        	 gp.gameState = gp.gameOverState;
        	 //gp.playSE(2);
         }
     }
     public void pauseState(int code) {
    	 if (code == KeyEvent.VK_P){
             gp.gameState = gp.playState;
         }
     }
     public void dialogueState(int code) {
    	 if (code == KeyEvent.VK_ENTER) {
     	    gp.ui.handleDialogueEnter();
     	}
     }
     public void characterState(int code) {
    	if(code == KeyEvent.VK_C) {
     		gp.gameState = gp.playState;
     	}
    	if(code == KeyEvent.VK_W) {
    		if(gp.ui.slotRow !=0) {
    		gp.ui.slotRow--;
    		}
    	}
    	if(code == KeyEvent.VK_A) {
    		if(gp.ui.slotCol !=0) {
    		gp.ui.slotCol--;
    		}
    	}
    	if(code == KeyEvent.VK_S) {
    		if(gp.ui.slotRow !=3) {
    			gp.ui.slotRow++;
    		}
    		
    	}
    	if(code == KeyEvent.VK_D) {
    		if(gp.ui.slotCol !=4) {
    			gp.ui.slotCol++;
    		}  		
    	}
     }
     public void lockpickState(int code) {
    	 	if (code == KeyEvent.VK_ESCAPE) {
    		    gp.ui.lockPickActive = false;
    		    gp.gameState = gp.playState;
    		    gp.ui.showMessage("Lockpicking cancelado.");
    		    return;
    		}
    	    if (code == KeyEvent.VK_ENTER) {
    	        int margin = 10;
    	        int diff = Math.abs(gp.ui.lockPickAngle - gp.ui.lockPickSweetSpot);
    	        if (diff > 180) diff = 360 - diff;

    	        if (diff <= margin) {
    	            // Acertou!
    	            if (gp.ui.lockPickStage < 3) {
    	                gp.ui.lockPickStage++;
    	                gp.ui.lockPickSpeed += 1; // aumenta dificuldade
    	                gp.ui.lockPickSweetSpot = (int)(Math.random() * 360);
    	                gp.playSE(2);
    	                gp.ui.showMessage("Etapa " + (gp.ui.lockPickStage - 1) + " concluída!");
    	            } else {
    	                // Última fase completa!
    	                gp.ui.lockPickTarget.locked = false;
    	                gp.ui.lockPickActive = false;
    	                gp.ui.lockPickSucess = true;
    	                gp.gameState = gp.playState;
    	                gp.playSE(3);
    	                gp.ui.showMessage("Porta destrancada!");
    	            }
    	        } else {
    	            // Errou
    	            gp.ui.showMessage("Falhou! Começando de novo.");
    	            gp.ui.lockPickStage = 1;
    	            gp.ui.lockPickSpeed = 2;
    	            gp.ui.lockPickSweetSpot = (int)(Math.random() * 360);
    	        }
    	    }
    	}
     public void powerBoxState(int code) {
    	    if (code == KeyEvent.VK_ESCAPE) {
    	        gp.ui.powerPuzzleActive = false;
    	        gp.gameState = gp.playState;
    	        gp.ui.showMessage("Puzzle cancelado.");
    	        return;
    	    }

    	    if (code == KeyEvent.VK_W) {
    	        if (gp.ui.choosingLeft && gp.ui.wireCursorLeft > 0) gp.ui.wireCursorLeft--;
    	        if (!gp.ui.choosingLeft && gp.ui.wireCursorRight > 0) gp.ui.wireCursorRight--;
    	    }
    	    if (code == KeyEvent.VK_S) {
    	        if (gp.ui.choosingLeft && gp.ui.wireCursorLeft < 3) gp.ui.wireCursorLeft++;
    	        if (!gp.ui.choosingLeft && gp.ui.wireCursorRight < 3) gp.ui.wireCursorRight++;
    	    }

    	    if (code == KeyEvent.VK_A || code == KeyEvent.VK_D) {
    	        gp.ui.choosingLeft = !gp.ui.choosingLeft;
    	    }

    	    if (code == KeyEvent.VK_ENTER) {
    	        if (gp.ui.choosingLeft) {
    	            if (!gp.ui.wireConnected[gp.ui.wireCursorLeft]) {
    	                gp.ui.wireSelectedLeft = gp.ui.wireCursorLeft;
    	                gp.ui.choosingLeft = false;
    	            }
    	        } else {
    	            int left = gp.ui.wireSelectedLeft;
    	            int right = gp.ui.wireCursorRight;

    	            if (left != -1 && !gp.ui.wireConnected[left]) {
    	                int expectedRight = gp.ui.wireMatches[left];
    	                if (expectedRight == right) {
    	                    gp.ui.playerConnections[left] = right;
    	                    gp.ui.wireConnected[left] = true;
    	                    gp.playSE(3);
    	                } else {
    	                    gp.playSE(2);
    	                    gp.ui.showMessage("Ligação errada!");
    	                }
    	                gp.ui.wireSelectedLeft = -1;
    	                gp.ui.choosingLeft = true;
    	            }

    	            boolean allConnected = true;
    	            for (boolean b : gp.ui.wireConnected) {
    	                if (!b) {
    	                    allConnected = false;
    	                    break;
    	                }
    	            }

    	            if (allConnected) {
    	                gp.ui.powerPuzzleActive = false;
    	                gp.ui.powerRestored = true;
    	                gp.doorLocked = 1;
    	                gp.eManager.lighting = null;
    	                gp.ui.showMessage("Energia restaurada!");
    	                gp.playSE(3);
    	                gp.gameState = gp.playState;
    	            }
    	        }
    	    }
    	}
     public void optionsState(int code) {
    	 if(code == KeyEvent.VK_ESCAPE) {
    		 gp.gameState = gp.playState;
    	 }
    	 if(code == KeyEvent.VK_ENTER) {
    		 enterPressed = true;
    	 }
    	 
    	 int maxCommandNum = 0;
    	 switch(gp.ui.subState) {
    	     case 0: maxCommandNum = 5; break; // menu principal
    	     case 1: maxCommandNum = 0; break; // notificação de tela cheia (só tem "VOLTAR")
    	     case 2: maxCommandNum = 0; break; // tela de controles (só tem "VOLTAR")
    	     case 3: maxCommandNum = 1; break; // confirmação de saída (tem "SIM" e "NÃO")
    	 }

    	 
    	 if(code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		gp.playSE(3);
    		if(gp.ui.commandNum < 0) {
    			gp.ui.commandNum = maxCommandNum;
    		}
    	 }
    	 if(code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		gp.playSE(3);
    		if(gp.ui.commandNum > maxCommandNum) {
    			gp.ui.commandNum = 0;
    		}
    	 }
    	 
    	 if(code == KeyEvent.VK_A) {
    		
    		 if(gp.ui.subState == 0) {
    			 if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
    				 gp.music.volumeScale--;
    				 gp.music.checkVolume();
    				 gp.playSE(3);
    			 }
    			 
    			 if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
        			 gp.se.volumeScale--;
        			 gp.playSE(3);
        			 }
        		 }
    		 }
    	 
    	 if(code == KeyEvent.VK_D) {
    		 
    		 if(gp.ui.subState == 0) {
    			 if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
    				 gp.music.volumeScale++;
    				 gp.music.checkVolume();
    				 gp.playSE(3);
    			 }
    			 if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
    				 gp.se.volumeScale++;
    				 gp.playSE(3);
    			 }
    		 }
    		 
    	 }
    	 
    	 
     }
     
     public void cutsceneState(int code) {
    	    // Só chama o next() se não estiver em uma escolha
    	    if (code == KeyEvent.VK_ENTER) {
    	        gp.cutsceneManager.next();
    	    }
     }
     
     public void choiceState(int code) {
    	    long currentTime = System.currentTimeMillis();

    	    if (currentTime - gp.ui.lastChoiceMoveTime > gp.ui.choiceMoveCooldown) {
    	        if (code == KeyEvent.VK_A) {
    	            gp.ui.selectedChoiceIndex = (gp.ui.selectedChoiceIndex + gp.ui.choiceOptions.length - 1) % gp.ui.choiceOptions.length;
    	            gp.ui.lastChoiceMoveTime = currentTime;
    	            gp.playSE(3);
    	        }
    	        if (code == KeyEvent.VK_D) {
    	            gp.ui.selectedChoiceIndex = (gp.ui.selectedChoiceIndex + 1) % gp.ui.choiceOptions.length;
    	            gp.ui.lastChoiceMoveTime = currentTime;
    	            gp.playSE(3);
    	        }
    	    }

    	    if (code == KeyEvent.VK_ENTER) {
    	        gp.ui.choiceResult = (gp.ui.selectedChoiceIndex == 0); // true = Sim, false = Não
    	        gp.ui.choiceActive = false;
    	        gp.gameState = gp.playState;
    	        gp.playSE(3);

    	        if (gp.ui.choiceResult) {
    	            // Jogador escolheu "Sim"
    	            System.out.println("Escolheu SIM");
    	            gp.cutsceneManager.startCutscene("end1");
    	        } else {
    	            // Jogador escolheu "Não"
    	            System.out.println("Escolheu NÃO");
    	            gp.cutsceneManager.startCutscene("end2");
    	        }
    	    }
    	}



     public void culpritSelectionState(int code) {
    	    long currentTime = System.currentTimeMillis();

    	    // Verifica se o tempo de cooldown já passou
    	    if (currentTime - lastMoveTime > moveCooldown) {
    	        if (code == KeyEvent.VK_A) {
    	            gp.ui.selectedCulpritIndex--;
    	            if (gp.ui.selectedCulpritIndex < 0) {
    	                gp.ui.selectedCulpritIndex = 4; // Lógica para voltar ao final se o índice for negativo
    	            }
    	            gp.playSE(3); // Toca o som de seleção
    	            lastMoveTime = currentTime; // Atualiza o tempo do último movimento
    	        }

    	        if (code == KeyEvent.VK_D) {
    	            gp.ui.selectedCulpritIndex++;
    	            if (gp.ui.selectedCulpritIndex > 4) {
    	                gp.ui.selectedCulpritIndex = 0; // Lógica para voltar ao início se o índice ultrapassar 4
    	            }
    	            gp.playSE(3); // Toca o som de seleção
    	            lastMoveTime = currentTime; // Atualiza o tempo do último movimento
    	        }
    	        
    	        if (code == KeyEvent.VK_ENTER) {
    	            gp.ui.culpritChosen = gp.ui.selectedCulpritIndex;
    	            
    	            // Se ele seleciona o Culpado Certo da continuidade aos estagios dos jogo
    	            if(gp.ui.culpritChosen == 3) {
    	            	gp.gameState = gp.playState;
    	            	gp.gameStage.currentStage = 4;
    	            } else {
    	            	// Abre tela de gameOver
    	            	gp.gameState = gp.gameOverState;
    	            }
    	        }
    	    }
    	}
     public void gameOverState(int code) {
    	 if(code == KeyEvent.VK_W) {
    		 gp.ui.commandNum--;
    		 if(gp.ui.commandNum < 0) {
    			 gp.ui.commandNum = 1;
    		 }
    		 gp.playSE(3);
    	 }
    	 if(code == KeyEvent.VK_S) {
    		 gp.ui.commandNum++;
    		 if(gp.ui.commandNum > 1) {
    			 gp.ui.commandNum = 0;
    		 }
    		 gp.playSE(3);
    	 }
    	 if(code == KeyEvent.VK_ENTER) {
    		 if(gp.ui.commandNum == 0) {
    			 gp.gameState = gp.playState;
    			 gp.retry();
    		 }
    		 else if(gp.ui.commandNum == 1) {
    			 gp.gameState = gp.titleState;
    			 gp.restart();
    		 }
    	 }
     }
     
    // Método chamado quando uma tecla é solta
    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode(); // Obtém o código da tecla solta
        
        // Verifica qual tecla foi solta e desativa a respectiva variável
        if (code == KeyEvent.VK_W) {    
            upPressed = false;
        }
        
        if (code == KeyEvent.VK_S) {    
            downPressed = false;
        }
        
        if (code == KeyEvent.VK_A) {    
            leftPressed = false;
        }
        
        if (code == KeyEvent.VK_D) {    
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {    
            fPressed = false;
        }
        
        if (code == KeyEvent.VK_ENTER) {    
            enterPressed = false;
        }
    }
}

