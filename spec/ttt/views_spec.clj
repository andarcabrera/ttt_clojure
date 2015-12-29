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

(describe "Move prompt"
  (it "prompts player to make a move "
    (should= "Make your move, " player-prompt)))



