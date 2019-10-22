import csv
import operator
from datetime import datetime
import random
 
import math


def loadCSV(filename):
    lines = csv.reader(open(filename, "rt"))
    dataset = list(lines)
    return dataset[1:2000]

def splitDataset(dataset,splitratio):
	trainSize = int(len(dataset)*splitratio)
	trainSet = []
	copy = list(dataset)
	while (len(trainSet)<trainSize):
		index = random.randrange(len(copy))
		trainSet.append(copy.pop(index))
	return [trainSet,copy]


def clean_and_transform_dataset(dataset):

    transformed_dataset = list()
    for i in range(1, len(dataset)):
        new_row = list()
        row = dataset[i]
        new_row.append(row[0])  
        startTime = datetime.strptime(row[1], '%Y-%m-%d %H:%M:%S')
        endTime = datetime.strptime(row[2], '%Y-%m-%d %H:%M:%S')
        tripTime = endTime.timestamp() - startTime.timestamp()
        new_row.append(tripTime)
        new_row.append(row[3]) 
        new_row.append(row[5])  
        new_row.append(row[8]) 
        transformed_dataset.append(new_row)
    return transformed_dataset

def euclideanDistance(instance1,instance2,length):
    distance = 0.0
    for i in range(length):
        distance += pow((float(instance1[i]) - float(instance2[i])), 2)
    return math.sqrt(distance)

def getNeighbours(trainingSet,testInstance,K):
    distances = list()
    length = len(trainingSet[0])-1
    for x in range(len(trainingSet)):
        dist = euclideanDistance(trainingSet[x],testInstance,length)
        distances.append((trainingSet[x],dist))
    distances.sort(key=operator.itemgetter(1))
    neighbors = list()
    for x in range(K):
        neighbors.append(distances[x][0])
    return neighbors

def getAnswer(neighbours):
    classVotes = dict()
    for x in range(len(neighbours)):
        response = neighbours[x][-1]
        if response in classVotes:
            classVotes[response] += 1
        else:
            classVotes[response] = 1
    sortedVotes = sorted(classVotes.items(), key=operator.itemgetter(1), reverse=True)
    return sortedVotes[0][0]

def getAccuracy(testData,predictions):
    correct = 0
    for i in range(len(testData)):
        if predictions[i] == testData[i][-1]:
            correct+=1
    accuracy = correct/len(testData)*100;
    return accuracy

filename = "tripdata.csv"
dataset = loadCSV(filename)
dataset = clean_and_transform_dataset(dataset)

splitRatio = 0.65

trainingSet,testSet = splitDataset(dataset,splitRatio)

predictions = []

for item in testSet:
    neighbours = getNeighbours(trainingSet,item,4)
    answer = getAnswer(neighbours)
    predictions.append(answer)

accuracy = getAccuracy(testSet,predictions)
print("Test Dataset Accuracy: ",accuracy, "%")



