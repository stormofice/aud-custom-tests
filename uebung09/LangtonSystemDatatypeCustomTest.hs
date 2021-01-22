---------------- TESTING ----------------
assert msg a b = 
    if a == b then
        putStrLn ("✓ [Test passed] : \"" ++ msg ++ "\"")
    else putStrLn ("x [Test failed] : \"" ++ msg ++ "\" ... expected \"" ++ (show b) ++ "\", got \"" ++ (show a) ++ "\"")
main = do
    putStrLn "=============== Langton Public Test ================"
    assert "flip BLACK" (Main.flip BLACK) WHITE
    assert "flip WHITE" (Main.flip BLACK) WHITE
    putStrLn "===================================================="
    assert "turnCW S" (turnCounterCW S) E
    assert "turnCounterCW E" (turnCounterCW E) N
    assert "turnCW (turnCounterCW N)" (turnCW (turnCounterCW N)) N
    assert "turnCW (turnCounterCW S)" (turnCW (turnCounterCW S)) S
    assert "turnCW (turnCounterCW E)" (turnCW (turnCounterCW E)) E
    assert "turnCW (turnCounterCW W)" (turnCW (turnCounterCW W)) W
    assert "several turnCW / turnCounterCW" (turnCounterCW (turnCounterCW (turnCounterCW (N)))) (turnCW N)
    assert "several turnCW / turnCounterCW" (turnCounterCW N) (turnCW (turnCW (turnCW N)))
    assert "several turnCW / turnCounterCW" (turnCounterCW (turnCounterCW (turnCounterCW (turnCounterCW E)))) (turnCW (turnCW (turnCW (turnCW E))))
    putStrLn "===================================================="
    assert "getX/getY with New" (getX (New E), getY (New W)) (0, 0)
    assert "getX/getY with one step" (getX (Step(New E)), getY (Step(New S))) (1, -1)
    assert "getX/getY with two steps" (getX (Step(Step(New W))), getY (Step(Step(New W)))) (-1, 1)
    assert "getX/getY with three steps" (getX (Step(Step(Step(New N)))), getY (Step(Step(Step(New N))))) (1, 0)
    
	putStrLn "===================================================="
    assert "getCol with New" (getCol (New W) 42 69) WHITE
    assert "getCol with one step" (getCol (Step(New N)) 0 0, getCol (Step(New N)) 0 1) (BLACK, WHITE)
    assert "getCol with two steps" (getCol (Step(Step(New S))) 0 0, getCol (Step(Step(New S))) 0 (-1), 
                                        getCol (Step(Step(New S))) 1 (-1)) (BLACK, BLACK, WHITE)
    putStrLn "===================================================="
    assert "getDir with New" (getDir (New W)) W
    assert "getDir with one step" (getDir (Step(New W))) N
    assert "getDir with two steps" (getDir (Step(Step(New S)))) N
    putStrLn "===================================================="
    let largeadt = foldl (\x y -> Step x) (New N) [0..13]
    assert "Large LSD - getDir" (getDir largeadt) S
    assert "Large LSD - getX"  (getX largeadt) (1)
    assert "Large LSD - getY" (getY largeadt) 1
    assert "Large LSD - getCol" (getCol largeadt (getX largeadt) (getY largeadt)) BLACK