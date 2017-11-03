(ns clojure-automata.core
  (:gen-class)
  (:require [clojure.string :as str]))

(def world [nil true nil true true nil])

(defn print-world [world]
  (println (str/join
            (map (fn [state] (if state \X \.)) world))))



(defn update-state [l c r]
  (cond
    (and l r) nil
    (or l r) true
    true nil))

(defn update-world [world]
  (let [states (for [i (range (count world))]
                 (map #(get-cell world %) [(- i 1) i (+ i 1)]))]
    (mapv #(apply update-state %) states)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn get-cell [world i]
  (cond
    (< i 0) nil
    (>= i (count world)) nil
    true (world i)))

(defn ncycles [world i]
  (if (= 0 i)
    nil
    (do
      (print-world world)
      (ncycles (update-world world) (- i 1)))))
