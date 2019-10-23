import pandas as pd
import os
import matplotlib.pyplot as plt

FILE = "Iris.csv"
data = pd.read_csv(FILE,encoding='utf-8')

cols = data.columns

print("Dataset Features Description")
print(data.describe())

print("Number of Columns", len(cols))


for i in range(1,5):

	feature = cols[i]
	data[feature].plot(kind='hist',bins=150)

	label = feature.split("Cm")[0]
	plt.title(label + ' Distribution')
	plt.xlabel(label)
	plt.show()


labels = list()
for i in range(1,5):
	labels.append(cols[i])

data.drop(cols[0],1,inplace=True)
data.boxplot(labels)

plt.title("Box Plot")
plt.show()
