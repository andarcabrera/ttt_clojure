(ns ttt.player-spec
  (:require [speclj.core :refer :all]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.player :refer :all]))

(describe "player-name"
  (around [it]
    (with-redefs
      [input/get-user-input #(str "Anda")
      [output/prompt (fn [& _])]]
      (it)))
  (it "contains the player name"
    (should= "Anda" ((player) :name))))

(describe "player-marker"
  (around [it]
    (with-redefs
      [input/get-user-input (constantly "X")
      [output/prompt (fn [& _])]]
      (it)))
  (it "contains the player marker"
    (should= "X"
        ((player) :marker))))