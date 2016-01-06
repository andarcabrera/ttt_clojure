(ns ttt.views)

(def welcome-message "Welcome to my TTT!")

(def player-name "Please enter player name:")

(def player-marker "Please enter player marker:")

(def board-size "Please select board-size:\n\t1.\t3X3 board\n\t2.\t4X4 board")

(def invalid-board-size "That is an invalid entry. Please select option 1 or 2.")

(def spot-selection "Make your move, ")

(def invalid-spot "That is an invalid spot. Please select an available spot.")

(def winning-message "Congratulations, you won, ")

(def tie-message "Bummer, it's a tie.")

(def duplicate-marker "The marker cannot be the same as the other player's. Please select a valid marker.")

(def no-input "You have not entered anything. Please enter the required info.")

(def game-type "What kind of TTT would you like to play?\n\t1.\tHuman vs. Human\n\t2.\tHuman vs. Computer\n\t3.\tComputer vs. Computer")

(def invalid-game-type "That is an invalid entry. Please select option 1, 2 or 3.")

(def starting-player "Would you like to start with the below player? (y/n)\n")

(def invalid-starting-player "That is an invalid entry. Please enter Y for yes or N for no")

(def computer-move "The computer is thinking...")