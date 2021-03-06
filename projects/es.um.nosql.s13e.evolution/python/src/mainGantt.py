import sys
from charts.GanttChart import GanttChart

REDDIT_CSV_ROUTE = "../../output/reddit/reddit.csv"
REDDIT_CSV_OUTLIERS_ROUTE = "../../output/reddit/reddit_filtered.csv"
REDDIT_CSV_LIVEVARS_ROUTE = "../../output/reddit/reddit.csv"
SOF_CSV_ROUTE = "../../output/stackoverflow/stackoverflow.csv"
SOF_CSV_OUTLIERS_ROUTE = "../../output/stackoverflow/stackoverflow_outliers.csv"
SOF_CSV_LIVEVARS_ROUTE = "../../output/stackoverflow/stackoverflow_livevars.csv"

def main():

  chartCreator = GanttChart(REDDIT_CSV_ROUTE)
  chartCreator.showCharts()

main()
