<template>
  <div style="padding: 30px;" class="bg-light">
    <calendar-heatmap
      end-date="2023-04-02"
      tooltip-unit="贡献"
      :range-color="['#ebedf0', '#9be9a8', '#40c463', '#30a14e', '#216e39']"
      :values="timeValue"/>
  </div>
</template>
<script>
import {CalendarHeatmap} from "vue-calendar-heatmap";

export default {
  data() {
    return {
      timeValue: [
        {date: "2023-04-01", count: 12},
        {date: "2023-04-02", count: 6},
        {date: "2023-03-31", count: 6},
        {date: "2023-03-30", count: 8},
        {date: "2023-03-30", count: 2},
        {date: "2023-03-29", count: 1},
        {date: "2023-03-28", count: 15},
        {date: "2023-03-27", count: 1},
        {date: "2023-03-26", count: 6},
        {date: "2023-03-25", count: 2},
        {date: "2023-03-24", count: 10},
        {date: "2023-03-23", count: 2},
        {date: "2023-03-22", count: 3},
        {date: "2023-03-21", count: 2},
        {date: "2023-03-20", count: 8},
        {date: "2023-03-19", count: 2},
        {date: "2023-03-18", count: 2},
        {date: "2023-03-17", count: 4},
        {date: "2023-03-16", count: 5},
        {date: "2023-03-15", count: 9},
        {date: "2023-03-14", count: 5},
        {date: "2023-03-13", count: 2},
        {date: "2023-03-12", count: 10},
        {date: "2023-02-28", count: 10},
        {date: "2023-02-27", count: 2},
        {date: "2023-02-21", count: 15},
        {date: "2023-02-20", count: 5},
        {date: "2023-02-19", count: 2},
        {date: "2022-01-01", count: 14},
        {date: "2022-01-02", count: 14},
        {date: "2022-01-03", count: 0},
        {date: "2022-01-04", count: 2},
        {date: "2022-01-05", count: 11}, {
          date: "2022-01-06",
          count: 6
        }, {date: "2022-01-07", count: 3}, {date: "2022-01-08", count: 1}, {
          date: "2022-01-09",
          count: 8
        }, {date: "2022-01-10", count: 12}, {date: "2022-01-11", count: 0}, {
          date: "2022-01-12",
          count: 10
        }, {date: "2022-01-13", count: 4}, {date: "2022-01-14", count: 10}, {
          date: "2022-01-15",
          count: 6
        }, {date: "2022-01-16", count: 2}, {date: "2022-01-17", count: 10}, {
          date: "2022-01-18",
          count: 11
        }, {date: "2022-01-19", count: 7}, {date: "2022-01-20", count: 0}, {
          date: "2022-01-21",
          count: 10
        }, {date: "2022-01-22", count: 9}, {date: "2022-01-23", count: 6}, {
          date: "2022-01-24",
          count: 11
        }, {date: "2022-01-25", count: 12}, {date: "2022-01-26", count: 4}, {
          date: "2022-01-27",
          count: 12
        }, {date: "2022-01-28", count: 4}, {date: "2022-01-29", count: 0}, {
          date: "2022-01-30",
          count: 14
        }, {date: "2022-01-31", count: 0}, {date: "2022-02-01", count: 2}, {
          date: "2022-02-02",
          count: 7
        }, {date: "2022-02-04", count: 11}, {
          date: "2022-02-05",
          count: 8
        }, {date: "2022-02-06", count: 3}, {date: "2022-02-07", count: 1}, {
          date: "2022-02-08",
          count: 6
        }, {date: "2022-02-09", count: 7}, {
          date: "2022-02-11",
          count: 2
        }, {date: "2022-02-12", count: 8}, {date: "2022-02-13", count: 11}, {
          date: "2022-02-14",
          count: 4
        }, {date: "2022-02-15", count: 15}, {date: "2022-02-16", count: 3}, {
          date: "2022-02-17",
          count: 11
        }, {date: "2022-02-18", count: 12}, {date: "2022-02-19", count: 1}, {
          date: "2022-02-20",
          count: 15
        }, {date: "2022-02-21", count: 7}, {date: "2022-02-22", count: 7}, {
          date: "2022-02-23",
          count: 4
        }, {date: "2022-02-24", count: 11}, {date: "2022-02-25", count: 13}, {
          date: "2022-02-26",
          count: 13
        }, {date: "2022-02-27", count: 2}, {date: "2022-02-28", count: 2}, {
          date: "2022-03-01",
          count: 2
        }, {date: "2022-03-02", count: 5}, {date: "2022-03-03", count: 11}, {
          date: "2022-03-04",
          count: 6
        }, {date: "2022-03-05", count: 1}, {
          date: "2022-03-07",
          count: 1
        }, {date: "2022-03-08", count: 5}, {date: "2022-03-09", count: 2}, {
          date: "2022-03-10",
          count: 15
        }, {date: "2022-03-11", count: 8}, {date: "2022-03-12", count: 1}, {
          date: "2022-03-13",
          count: 12
        }, {date: "2022-03-14", count: 14}, {date: "2022-03-15", count: 9}, {
          date: "2022-03-16",
          count: 14
        }, {date: "2022-03-17", count: 8}, {date: "2022-03-18", count: 1}, {
          date: "2022-03-19",
          count: 5
        }, {date: "2022-03-21", count: 10}, {
          date: "2022-03-22",
          count: 14
        }, {date: "2022-03-23", count: 1}, {date: "2022-03-24", count: 8}, {
          date: "2022-03-25",
          count: 4
        }, {date: "2022-03-26", count: 1}, {date: "2022-03-27", count: 5}, {
          date: "2022-03-28",
          count: 8
        }, {date: "2022-03-29", count: 4}, {date: "2022-03-30", count: 0}, {
          date: "2022-03-31",
          count: 5
        }, {date: "2022-04-01", count: 5}, {
          date: "2022-04-03",
          count: 7
        }, {date: "2022-04-04", count: 0}, {date: "2022-04-05", count: 15}, {
          date: "2022-04-06",
          count: 0
        }, {date: "2022-04-07", count: 1}, {date: "2022-04-08", count: 2}, {
          date: "2022-04-09",
          count: 2
        }, {date: "2022-04-10", count: 7}, {date: "2022-04-11", count: 1}, {
          date: "2022-04-12",
          count: 12
        }, {date: "2022-04-13", count: 4}, {date: "2022-04-14", count: 1}, {date: "2022-04-16", count: 3}, {date: "2022-04-17", count: 10}, {
          date: "2022-04-18",
          count: 10
        }, {date: "2022-04-19", count: 11},  {
          date: "2022-04-21",
          count: 3
        }, {date: "2022-04-22", count: 1}, {date: "2022-04-23", count: 4}, {
          date: "2022-04-24",
          count: 12
        }, {date: "2022-04-25", count: 7}, {date: "2022-04-26", count: 8}, {
          date: "2022-04-27",
          count: 13
        }, {date: "2022-04-29", count: 0}, {
          date: "2022-04-30",
          count: 4
        }, {date: "2022-05-01", count: 1}, {date: "2022-05-02", count: 1}, {
          date: "2022-05-03",
          count: 15
        }, {date: "2022-05-04", count: 2}, {date: "2022-05-05", count: 9}, {
          date: "2022-05-06",
          count: 2
        }, {date: "2022-05-07", count: 1},  {
          date: "2022-05-09",
          count: 13
        }, {date: "2022-05-10", count: 9}, {date: "2022-05-11", count: 13}, {
          date: "2022-05-12",
          count: 2
        },  {date: "2022-05-14", count: 5}, {
          date: "2022-05-15",
          count: 10
        }, {date: "2022-05-16", count: 9}, {date: "2022-05-17", count: 1}, {
          date: "2022-05-18",
          count: 15
        },{date: "2022-05-20", count: 2}, {
          date: "2022-05-21",
          count: 8
        }, {date: "2022-05-22", count: 9}, {date: "2022-05-23", count: 2}, {
          date: "2022-05-24",
          count: 0
        }, {date: "2022-05-25", count: 3},{
          date: "2022-05-27",
          count: 7
        }, {date: "2022-05-28", count: 8}, {date: "2022-05-29", count: 14}, {
          date: "2022-05-30",
          count: 11
        }, {date: "2022-05-31", count: 8}, {date: "2022-06-01", count: 1}, {
          date: "2022-06-02",
          count: 13
        }, {date: "2022-06-03", count: 1},{
          date: "2022-06-05",
          count: 13
        }, {date: "2022-06-06", count: 1}, {date: "2022-06-07", count: 1}, {
          date: "2022-06-08",
          count: 12
        }, {date: "2022-06-09", count: 0}, {date: "2022-06-10", count: 9}, {
          date: "2022-06-11",
          count: 4
        }, {date: "2022-06-12", count: 15}, {date: "2022-06-13", count: 1}, {
          date: "2022-06-14",
          count: 15
        }, {date: "2022-06-15", count: 9}, {date: "2022-06-16", count: 6}, {
          date: "2022-06-17",
          count: 6
        }, {date: "2022-06-18", count: 8}, {date: "2022-06-19", count: 1}, {
          date: "2022-06-20",
          count: 8
        }, {date: "2022-06-21", count: 1}, {date: "2022-06-22", count: 2}, {
          date: "2022-06-23",
          count: 6
        }, {date: "2022-06-24", count: 4}, {date: "2022-06-25", count: 12}, {
          date: "2022-06-26",
          count: 2
        }, {date: "2022-06-27", count: 8}, {date: "2022-06-28", count: 9}, {
          date: "2022-06-29",
          count: 12
        }, {date: "2022-06-30", count: 1}, {date: "2022-07-01", count: 1}, {
          date: "2022-07-02",
          count: 7
        }, {date: "2022-07-03", count: 0}, {date: "2022-07-04", count: 9}, {
          date: "2022-07-05",
          count: 11
        } ,  {date: "2022-07-10", count: 2}, {
          date: "2022-07-11",
          count: 10
        }, {date: "2022-07-12", count: 1}, {date: "2022-07-13", count: 2}, {
          date: "2022-07-14",
          count: 8
        }, {date: "2022-07-15", count: 3}, {date: "2022-07-16", count: 9}, {
          date: "2022-07-17",
          count: 8
        }, {date: "2022-07-18", count: 12}, {date: "2022-07-19", count: 10}, {
          date: "2022-07-20",
          count: 13
        }, {date: "2022-07-21", count: 1}, {date: "2022-07-22", count: 8}, {
          date: "2022-07-23",
          count: 8
        }, {date: "2022-07-24", count: 13},{
          date: "2022-07-26",
          count: 6
        }, {date: "2022-07-27", count: 1}, {date: "2022-07-28", count: 8}, {
          date: "2022-07-29",
          count: 7
        }, {date: "2022-07-30", count: 1}, {date: "2022-07-31", count: 3}, {
          date: "2022-08-01",
          count: 13
        }, {date: "2022-08-02", count: 4}, {date: "2022-08-03", count: 5}, {
          date: "2022-08-04",
          count: 8
        }, {date: "2022-08-05", count: 1},{
          date: "2022-08-07",
          count: 14
        }, {date: "2022-08-08", count: 1}, {date: "2022-08-09", count: 13}, {
          date: "2022-08-10",
          count: 2
        }, {date: "2022-08-11", count: 2}, {date: "2022-08-12", count: 2}, {
          date: "2022-08-13",
          count: 0
        }, {date: "2022-08-14", count: 14}, {date: "2022-08-15", count: 1}, {
          date: "2022-08-16",
          count: 1
        }, {date: "2022-08-17", count: 7}, {date: "2022-08-18", count: 11}, {
          date: "2022-08-19",
          count: 12
        }, {date: "2022-08-20", count: 1}, {date: "2022-08-21", count: 10}, {
          date: "2022-08-22",
          count: 3
        }, {date: "2022-08-23", count: 0}, {
          date: "2022-08-25",
          count: 13
        }, {date: "2022-08-27", count: 8}, {
          date: "2022-08-28",
          count: 6
        }, {date: "2022-08-30", count: 9}, {
          date: "2022-08-31",
          count: 10
        }, {date: "2022-09-01", count: 4}, {date: "2022-09-02", count: 9}, {
          date: "2022-09-03",
          count: 15
        }, {date: "2022-09-04", count: 9}, {date: "2022-09-05", count: 8}, {
          date: "2022-09-06",
          count: 9
        }, {date: "2022-09-07", count: 6}, {date: "2022-09-08", count: 6}, {
          date: "2022-09-09",
          count: 12
        }, {date: "2022-09-10", count: 11}, {
          date: "2022-09-12",
          count: 8
        }, {
          date: "2022-09-15",
          count: 0
        }, {date: "2022-09-16", count: 11}, {date: "2022-09-17", count: 9}, {
          date: "2022-09-18",
          count: 13
        }, {date: "2022-09-19", count: 2}, {date: "2022-09-20", count: 10}, {
          date: "2022-09-21",
          count: 12
        }, {date: "2022-09-22", count: 8}, {date: "2022-09-23", count: 5}, {
          date: "2022-09-24",
          count: 3
        }, {date: "2022-09-25", count: 1}, {date: "2022-09-26", count: 15}, {
          date: "2022-09-27",
          count: 7
        }, {date: "2022-09-28", count: 7}, {date: "2022-09-29", count: 1}, {
          date: "2022-09-30",
          count: 9
        }, {date: "2022-10-01", count: 12}, {date: "2022-10-02", count: 1}, {
          date: "2022-10-03",
          count: 3
        }, {date: "2022-10-10", count: 6}, {date: "2022-10-11", count: 7}, {
          date: "2022-10-12",
          count: 3
        }, {date: "2022-10-13", count: 1}, {
          date: "2022-10-15",
          count: 7
        }, {date: "2022-10-16", count: 6}, {date: "2022-10-17", count: 9}, {
          date: "2022-10-18",
          count: 15
        }, {date: "2022-10-19", count: 5},{date: "2022-10-23", count: 9}, {
          date: "2022-10-24",
          count: 7
        }, {date: "2022-10-25", count: 9}, {date: "2022-10-26", count: 9}, {
          date: "2022-10-27",
          count: 4
        }, {date: "2022-10-28", count: 10}, {date: "2022-10-29", count: 6}, {
          date: "2022-10-30",
          count: 10
        }, {date: "2022-10-31", count: 1}, {date: "2022-11-01", count: 10}, {
          date: "2022-11-02",
          count: 3
        }, {date: "2022-11-03", count: 8}, {date: "2022-11-04", count: 7}, {
          date: "2022-11-05",
          count: 0
        }, {date: "2022-11-06", count: 6}, {date: "2022-11-07", count: 12}, {
          date: "2022-11-08",
          count: 15
        }, {date: "2022-11-09", count: 10}, {date: "2022-11-10", count: 10}, {
          date: "2022-11-11",
          count: 7
        }, {date: "2022-11-12", count: 1}, {date: "2022-11-13", count: 4}, {
          date: "2022-11-14",
          count: 2
        }, {date: "2022-11-15", count: 14}, {date: "2022-11-18", count: 11}, {
          date: "2022-11-20",
          count: 0
        }, {date: "2022-11-21", count: 5}, {date: "2022-11-22", count: 8}, {
          date: "2022-11-23",
          count: 11
        }, {date: "2022-11-24", count: 5}, {date: "2022-11-25", count: 13}, {
          date: "2022-11-26",
          count: 1
        }, {date: "2022-11-27", count: 7}, {date: "2022-11-28", count: 0}, {
          date: "2022-11-29",
          count: 15
        }, {date: "2022-11-30", count: 11}, {date: "2022-12-01", count: 5}, {
          date: "2022-12-02",
          count: 13
        }, {date: "2022-12-03", count: 0}, {date: "2022-12-04", count: 11}, {
          date: "2022-12-05",
          count: 4
        }, {date: "2022-12-06", count: 4}, {date: "2022-12-07", count: 2}, {
          date: "2022-12-08",
          count: 10
        }, {date: "2022-12-09", count: 12}, {date: "2022-12-10", count: 5}, {
          date: "2022-12-11",
          count: 8
        }, {date: "2022-12-12", count: 9}, {date: "2022-12-13", count: 6}, {
          date: "2022-12-14",
          count: 14
        }, {date: "2022-12-15", count: 10}, {date: "2022-12-16", count: 7}, {
          date: "2022-12-17",
          count: 6
        }, {date: "2022-12-18", count: 4}, {date: "2022-12-19", count: 10}, {
          date: "2022-12-20",
          count: 3
        }, {date: "2022-12-21", count: 1}, {date: "2022-12-22", count: 11}, {
          date: "2022-12-23",
          count: 1
        }, {date: "2022-12-24", count: 7}, {date: "2022-12-25", count: 10}, {
          date: "2022-12-26",
          count: 11
        }, {date: "2022-12-27", count: 9}, {date: "2022-12-28", count: 4}, {
          date: "2022-12-29",
          count: 4
        }, {date: "2022-12-30", count: 12}, {date: "2022-12-31", count: 10}
      ],
    };
  },
  components: {
    CalendarHeatmap,
  },
};
</script>
<style lang="scss" scoped>
</style>
