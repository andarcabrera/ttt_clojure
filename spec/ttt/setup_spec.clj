(ns ttt.setup-spec
  (:require [speclj.core :refer :all]
            [ttt.input :as input]
            [ttt.output :as output]
            [ttt.setup :refer :all]))

(defn- make-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "board-size"
   (around [it]
    (with-redefs
      [ input/get-user-input #(str "2")
        output/prompt (constantly nil)]
      (it)))
  (it "asks for board-size"
    (should= 16
        (board-size))))

(describe "board-size"
  (around [it]
    (with-out-str (it)))

  (it "prompts user to re-enter selection if the selection is invalid until a valid selection is made"
    (should= 9
      (with-in-str (make-input '("nine" "1"))
        (board-size)))))


(describe "ordered-players"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to enter the player information"
    (should= [{:type "human" :name "Anda", :marker "X"} {:type "human" :name "Eli", :marker "Y"}]
      (with-in-str (make-input '("1" "Anda" "X" "Eli" "Y" "Y"))
        (ordered-players)))))

(describe "ordered-players"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to enter the player information until valid"
    (should= [{:type "human" :name "Anda", :marker "X"} {:type "human" :name "Eli", :marker "Y"}]
      (with-in-str (make-input '("1" "Anda" "X" "Eli" "X" "Y" "Y"))
        (ordered-players)))))

(describe "ordered-players"
  (around [it]
    (with-out-str (it)))

  (it "creates one computer and one human player"
    (should= [{:type "human" :name "Anda", :marker "X"} {:type "computer" :name "computer", :marker "Y"}]
      (with-in-str (make-input '("2" "Anda" "X" "Y" "Y"))
        (ordered-players)))))

(describe "ordered-players"
  (around [it]
    (with-out-str (it)))

  (it "creates two computer players"
    (should= [{:type "computer" :name "computer", :marker "X"} {:type "computer" :name "computer", :marker "Y"}]
      (with-in-str (make-input '("0" "3" "X" "Y" "Y"))
        (ordered-players)))))

(describe "ordered-players"
  (around [it]
    (with-out-str (it)))

  (it "reverses the player array if user chooses to start with the second inputed player"
    (should= [{:type "human" :name "Eli", :marker "Y"} {:type "human" :name "Anda", :marker "X"}]
      (with-in-str (make-input '("0" "1" "Anda" "X" "Eli" "Y" "N"))
        (ordered-players)))))

(describe "ordered-players"
  (around [it]
    (with-out-str (it)))

  (it "keeps the player array as is if user chooses to play in default order"
    (should= [{:type "human" :name "Eli", :marker "Y"} {:type "human" :name "Anda", :marker "X"}]
      (with-in-str (make-input '("0" "1" "Eli" "Y" "Anda" "X" "Y"))
        (ordered-players)))))


(describe "ordered-players"
  (around [it]
    (with-out-str (it)))

  (it "asks for player order until valid response is received"
    (should= [{:type "human" :name "Eli", :marker "Y"} {:type "human" :name "Anda", :marker "X"}]
      (with-in-str (make-input '("0" "1" "Eli" "Y" "Anda" "X" "g" "l" "Y"))
        (ordered-players)))))

(describe "game-type"
  (around [it]
    (with-out-str (it)))

  (it "asks for player spot choice"
    (should= 3
      (with-in-str "3"
        (game-type)))))

(describe "game-type"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user for game-type until a valid game-type is entered"
    (should= 2
      (with-in-str (make-input '("0" "9" "a" "2"))
        (game-type)))))



