(ns ttt.output-spec
  (:require [speclj.core :refer :all]
            [ttt.output :refer :all]))

(describe "prompt"
  (it "prompts user for input"
    (should= "What is your name?\n"
      (with-out-str "What is your name?"
        (prompt "What is your name?")))))