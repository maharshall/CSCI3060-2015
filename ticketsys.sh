# Run the tests, check the outputs
chdir Inputs
for f in *
do
    echo $f
    chdir $f
    for i in *
    do
        echo "Running test $f-$i"
        chdir ../..
        ./sys < Inputs/$f/$i > Outputs/$f/$i.out
        chdir Inputs/$f
    done
    chdir ../..
    chdir Inputs
done

chdir ..
rm all.etf
chdir Expected
for f in *
do
    chdir $f
    for i in *.etf
    do
        echo "At $f-$i"
        chdir ../..
        cat Expected/$f/$i >> all.etf
        chdir Expected/$f
    done
    chdir ../..
    chdir Expected
done

chdir ..

diff -c all.etf daily.txt > test.txt
