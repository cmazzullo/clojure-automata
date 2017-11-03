(import '(java.lang.Thread)) ;; Need this for the "sleep" function


(ns clojure-automata.core
  (:gen-class)
  (:require [clojure.string :as str]))


(defn print-world [world]
  "Prints out a representation of the current 'world'"
  (println (str/join
            (map (fn [state] (if state "8" " ")) world))))


(defn generate-rand-world [size]
  "Generates a random 'world', an array of true/false values of size 'size'"
  (apply vector
         (for [i (range size)]
           (= (rand-int 2) 1)))) ;; Generate random 0s and 1s, map to booleans


(defn update-state [l c r]
  "Returns the updated value of a state after one time step"
  (cond
    (and l r) nil
    (or l r) true
    true nil))


(defn get-cell [world i]
  (cond
    (< i 0) nil
    (>= i (count world)) nil
    true (world i)))


(defn update-world [world]
  "Returns an updated world after one time step"
  (let [states (for [i (range (count world))]
                 (map #(get-cell world %) [(- i 1) i (+ i 1)]))]
    (mapv #(apply update-state %) states)))


(defn ncycles [world i time]
  "Run 'i' cycles of updates on 'world', sleeping for 'time' ms"
  (if (= 0 i)
    nil
    (do
      (print-world world)
      (Thread/sleep time)
      (ncycles (update-world world) (- i 1) time))))


(defn -main
  "Main execution loop - prints out a random world's evolution once per second."
  [& args]
  ((ncycles (generate-rand-world 85) -1 1000)))
