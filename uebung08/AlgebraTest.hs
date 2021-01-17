-- How to use:
-- Copy paste the following into your own Haskell implementation
-- This does *not* use 'Nat' to represent Integers, so keep in mind that your axioms need to compare with zero and not one
main = do
    let kleinerKlein = kleiner 5 10 --0
    let kleinerGross = kleiner 10 5 --1
    let kleinerSame = kleiner 1 1 --1
    if kleinerKlein == 0 then putStrLn "Test passed: kleiner 5 10" else putStrLn "Test failed: kleiner 5 10"
    if kleinerGross == 1 then putStrLn "Test passed: kleiner 10 5" else putStrLn "Test failed: kleiner 10 5"
    if kleinerSame == 1 then putStrLn "Test passed: kleiner 1 1" else putStrLn "Test failed: kleiner 1 1"
   
    let groesserKlein = groesser 5 10 --1
    let groesserGross = groesser 10 5 --0
    let groesserSame = groesser 1 1 --1
    if groesserKlein == 1 then putStrLn "Test passed: groesser 5 10" else putStrLn "Test failed: groesser 5 10"
    if groesserGross == 0 then putStrLn "Test passed: groesser 10 5" else putStrLn "Test failed: groesser 10 5"    
    if groesserSame == 1 then putStrLn "Test passed: groesser 1 1" else putStrLn "Test failed: groesser 1 1"    

    let moduloZeroFirst = modulo 5 0 --0
    let moduloZeroLast = modulo 0 5 --0
    let moduloBig = modulo 723123 721 --681
    let moduloWithoutRest = modulo 16 2 --0
    let moduloWithoutRest1 = modulo 10 1 --0
    let moduloBackwards = modulo 150 200 --150

    if moduloZeroFirst == 0 then putStrLn "Test passed: modulo 5 0" else putStrLn "Test failed: modulo 5 0"        
    if moduloZeroLast == 0 then putStrLn "Test passed: modulo 0 5" else putStrLn "Test failed: modulo 0 5"        
    if moduloBig == 681 then putStrLn "Test passed: modulo 723123 721" else putStrLn "Test failed: modulo 723123 721"        
    if moduloWithoutRest == 0 then putStrLn "Test passed: modulo 16 2" else putStrLn "Test failed: modulo 16 2"        
    if moduloWithoutRest1 == 0 then putStrLn "Test passed: modulo 10 1" else putStrLn "Test failed: modulo 10 1"        
    if moduloBackwards == 150 then putStrLn "Test passed: modulo 150 200" else putStrLn "Test failed: modulo 150 200"        
    
    let fibSmallZero = fib 0 -- 0
    let fibSmallOne = fib 1 --1
    let fibMedium = fib 5 --5
    let fibMedium2 = fib 10 --55
    let fibBig = fib 15 --610
    let fibBig2 = fib 20 --6765

    if fibSmallZero== 0 then putStrLn "Test passed: fib 0" else putStrLn "Test failed: fib 0"
    if fibSmallOne == 1 then putStrLn "Test passed: fib 1" else putStrLn "Test failed: fib 1"
    if fibMedium == 5 then putStrLn "Test passed: fib 5" else putStrLn "Test failed: fib 5"
    if fibMedium2 == 55 then putStrLn "Test passed: fib 10" else putStrLn "Test failed: fib 10"
    if fibBig == 610 then putStrLn "Test passed: fib 15" else putStrLn "Test failed: fib 15"
    if fibBig2 == 6765 then putStrLn "Test passed: fib 30" else putStrLn "Test failed: fib 30"

    let ggtSmall = ggT 1 0 --1
    let ggtSmallReversed = ggT 0 1 --1
    let ggtSmallEasy = ggT 15 10 --5
    let ggtMedium = ggT 272 32 --16
    let ggtBig = ggT 6734 52 --26
    
    if ggtSmall == 1 then putStrLn "Test passed: ggT 1 0" else putStrLn "Test failed: ggT 1 0"
    if ggtSmallReversed == 1 then putStrLn "Test passed: ggT 0 1" else putStrLn "Test failed: ggT 0 1"
    if ggtSmallEasy == 5 then putStrLn "Test passed: ggT 15 10" else putStrLn "Test failed: ggT 15 10"
    if ggtMedium == 16 then putStrLn "Test passed: ggT 272 32" else putStrLn "Test failed: ggT 272 32"
    if ggtBig == 26 then putStrLn "Test passed: ggT 6734 52" else putStrLn "Test failed: ggT 6734 52"
    
    let kgvSmall = kgV 1 0 --0
    let kgvSmallReversed = kgV 0 1 --0
    let kgvSmall1 = kgV 10 5 --10
    let kgvSmall1R = kgV 5 10 --10
    let kgvMedium = kgV 56 13 --728
    let kgvBig = kgV 713 12 --8556

    if kgvSmall == 0 then putStrLn "Test passed: kgV 1 0" else putStrLn "Test failed: kgV 1 0"
    if kgvSmallReversed == 0 then putStrLn "Test passed: kgV 0 1" else putStrLn "Test failed: kgV 0 1"
    if kgvSmall1 == 10 then putStrLn "Test passed: kgV 10 5" else putStrLn "Test failed: kgV 10 5"
    if kgvSmall1R == 10 then putStrLn "Test passed: kgV 5 10" else putStrLn "Test failed: kgV 5 10"
    if kgvMedium == 728 then putStrLn "Test passed: kgV 56 13" else putStrLn "Test failed: kgV 56 13"
    if kgvBig == 8556 then putStrLn "Test passed: kgV 713 12" else putStrLn "Test failed: kgV 713 12"