(import '(java.lang.Thread)) ;; Need this for the "sleep" function

(ns clojure-automata.core
  (:gen-class)
  (:require [clojure.string :as str]))


(defn print-world [world]
  (println (str/join
            (map (fn [state] (if state "8" " ")) world))))


(defn generate-rand-world [size]
  (apply vector
         (for [i (range size)]
           (= (rand-int 2) 1)))) ;; Generate random 0s and 1s, map to booleans


(defn update-state [l c r]
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
  (let [states (for [i (range (count world))]
                 (map #(get-cell world %) [(- i 1) i (+ i 1)]))]
    (mapv #(apply update-state %) states)))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn ncycles [world i time]
  (if (= 0 i)
    nil
    (do
      (print-world world)
      (Thread/sleep time)
      (ncycles (update-world world) (- i 1) time))))
