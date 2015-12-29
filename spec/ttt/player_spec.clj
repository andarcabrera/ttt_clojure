(ns ttt.player-spec
  (:require [speclj.core :refer :all]
            [ttt.player :refer :all]))

(describe "player"
  (it "contains the player name"
    (with-out-str ""
    (should= "Anda"
      (with-in-str "Anda"
        (player :name))))))