# Run the tests, check the outputs

# change directory to inputs
# for each folder in inputs and
# for each file in those folders
# run the the input file against the program
# and send the output to the Output folder
# with respective folders and files with the ext .out
chdir Inputs
for f in *
do
    echo $ftilda c
    chdir $f
    for i in *
    do
        echo "Running test $f-$i"
        chdir ../..
        ./sys < Inputs/$f/$i > Outputs/$f/${i%.*}.out
        chdir Inputs/$f
    done
    chdir ../..
    chdir Inputs
done

# check transaction file differences

# concat every expected daily tranaction value
# into a file all.etf
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

# back to main folder
chdir ..

diff -c all.etf ../daily.txt > test.txt
# run diff command against expected daily transaction value
# store the differences in text.txt
diff -c all.etf daily.txt > ExpectedDiff.txt

rm diff.txt
# check terminal output differences

# compare the Expected Terminal Outputs against 
# Actual Terminal Outputs.
chdir Outputs
for f in *
do
    chdir $f
    for i in *
    do
        echo "At $f-$i"
        chdir ../..
        diff -c Expected/$f/${i%.*}.bto Outputs/$f/${i%.*}.out >> diff.txt
        chdir Outputs/$f
    done
    chdir ../..
    chdir Outputs
done

