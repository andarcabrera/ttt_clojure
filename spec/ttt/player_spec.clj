(ns ttt.player-spec
  (:require [speclj.core :refer :all]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.player :refer :all]))

(describe "player-name"
  (around [it]
    (with-redefs
      [ input/get-user-input #(str "Anda")
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player name"
    (should= "Anda" ((create-player) :name))))

(describe "player-marker"
  (around [it]
    (with-redefs
      [ input/get-user-input (constantly "X")
        output/prompt (constantly nil)]
      (it)))
  (it "contains the player marker"
    (should= "X"
        ((create-player) :marker))))

(describe "make-move"
  (around [it]
    (with-out-str (it)))

  (it "asks for player spot choice"
    (should= 3
      (with-in-str "3"
        (make-move)))))

; (describe "make-move"
;   (around [it]
;     (with-out-str (it)))

;   (it "asks for player spot choice"
;     (should= 3
;       (with-in-str (make-input '("nine" "1"))
;         (board-size)))))