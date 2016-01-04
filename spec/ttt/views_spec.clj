(ns ttt.views-spec
  (:require [speclj.core :refer :all]
            [ttt.views :refer :all]))

(describe "welcome-message"
  (it "displays a welcome-message"
    (should= "Welcome to my TTT!" welcome-message)))

(describe "player-name"
  (it "displays prompt for player name"
    (should= "Please enter player name:" player-name)))

(describe "player-marker"
  (it "displays prompt for player marker"
    (should= "Please enter player marker:" player-marker)))

(describe "board-size"
  (it "displays prompt for board size"
    (should= "Please select board-size:\n\t1.\t3X3 board\n\t2.\t4X4 board" board-size)))

(describe "invalid-board-size"
  (it "displays prompt for invalid board size"
    (should= "That is an invalid entry. Please select option 1 or 2." invalid-board-size)))

(describe "Spot selection"
  (it "prompts player to make a move "
    (should= "Make your move, " spot-selection)))

(describe "Invalid spot"
  (it "displays prompt for invalid spot "
    (should= "That is an invalid spot. Please select an available spot." invalid-spot)))


(describe "winning-message"
  (it "displays winning message"
    (should= "Congratulations, you won, " winning-message)))

(describe "tie-message"
  (it "displays a tie message"
    (should= "Bummer, it's a tie." tie-message)))

(describe "duplicate-marker"
  (it "displays an duplicate marker message"
    (should= "The marker cannot be the same as the other player's. Please select a valid marker." duplicate-marker)))

(describe "no-input"
  (it "displays a blank input message"
    (should= "You have not entered anything. Please enter the required info." no-input)))

(describe "board-size"
  (it "displays prompt for game type"
    (should= "What kind of TTT would you like to play?:\n\t1.\tHuman vs. Human\n\t2.\tHuman vs. Computer\n\t3. Computer vs. Computer" game-type)))



