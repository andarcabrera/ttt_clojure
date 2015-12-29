(ns ttt.views-spec
  (:require [speclj.core :refer :all]
            [ttt.views :refer :all]))

(describe "welcome-message"
  (it "displays a welcome-message"
    (should= "Welcome to my TTT!" welcome-message)))

(describe "player-name"
  (it "displays prompt for player name"
    (should= "Please enter player name:" player-name)))