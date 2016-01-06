(ns ttt.player
  (:require [ttt.input :as input]
            [ttt.output :as output]
            [ttt.views :as views]
            [ttt.board :as board]))

(defn- get-player-name []
  (output/prompt views/player-name)
  (input/get-user-input))

(defn get-player-marker []
  (output/prompt views/player-marker)
  (input/get-user-input))

(defn validate-input [input]
  (loop [selections (repeatedly input)]
    (let [selection (first selections)]
      (if (not= "" selection)
        selection
      (do (output/prompt views/no-input)
        (recur (next selections)))))))

(defn- spot-selection []
  (input/get-user-input))

(defn- invalid-spot []
  (output/prompt views/invalid-spot))

(defn valid-spots [board]
  (set (map #(str %) (range (count board)))))

(defn create-human-player []
  { :type "human"
    :name (validate-input get-player-name)
    :marker (validate-input get-player-marker)})

(defn create-computer-player []
  { :type "computer"
    :name "computer"
    :marker (validate-input get-player-marker)})

(defmulti select-spot :type)

(defmethod select-spot "human" [info]
  (loop [selections (repeatedly spot-selection)]
    (let [selection (first selections) board (info :board)]
      (if (and (contains? (valid-spots board) selection) (board/available-spot? board (Integer/parseInt selection)))
        (Integer/parseInt selection)
      (do (invalid-spot)
        (recur (next selections)))))))

(defn- game-over [board]
  (or (board/solved-board? board) (board/tied-board? board)))

(defn- possible-moves [board markers]
  (let [available-spots (board/all-available-spots board)]
  (pmap #(board/fill-spot board % (board/next-marker board markers)) available-spots)))

(defn- minimax-score [board markers player-marker]
  (cond
    (and (board/solved-board? board) (= (board/winning-marker board markers) player-marker))
      1
    (and (board/solved-board? board) (not= (board/winning-marker board markers) player-marker))
     -1
    (board/tied-board? board)
      0))

(defn- minimax [board markers player-marker]
  (let [current-score (minimax-score board markers player-marker)]
    (if (game-over board)
      current-score
      (do
        (let [board board markers markers player-marker player-marker]
           (if (not= (board/next-marker board markers) player-marker)
              (apply min (pmap #(minimax % markers player-marker)
                        (possible-moves board markers)))
              (apply max (pmap #(minimax % markers player-marker)
                        (possible-moves board markers)))))))))

(defn- moves-scores [board markers player-marker]
  (let [available-spots (board/all-available-spots board) markers markers player-marker player-marker]
    (map vector available-spots
               (map #(minimax % markers player-marker) (possible-moves board markers)))))

(defn- best-move [board markers player-marker]
  ; (println (moves-scores board markers player-marker))
  (key (apply max-key val (into {} (moves-scores board markers player-marker)))))

(defmethod select-spot "computer" [info]
  (let [board (info :board) markers (info :markers) player-marker (info :player-marker)]
    (if (= (count board) (count (board/all-available-spots board)))
      4
      (best-move board markers player-marker))))











