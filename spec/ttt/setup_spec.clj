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


(describe "players"
  (around [it]
    (with-out-str (it)))

  (it "prompts the user to enter the player information"
    (should= [{:name "Anda", :marker "X"} {:name "Eli", :marker "Y"}]
      (with-in-str (make-input '("Anda" "X" "Eli" "Y"))
        (players)))))

; (describe "players"
;   (around [it]
;     (with-out-str (it)))

  ; (it "prompts the user to enter the player information until valid"
  ;   (should= [{:name "Anda", :marker "X"} {:name "Eli", :marker "Y"}]
  ;     (with-in-str (make-input '("Anda" "X" "Eli" "X" "Y"))
  ;       (players)))))



