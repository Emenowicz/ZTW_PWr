<template>
  <v-menu
    ref="menu"
    lazy
    :close-on-content-click="false"
    v-model="menu"
    transition="scale-transition"
    offset-y
    full-width
    :nudge-right="40"
    min-width="290px"
    :return-value.sync="date">
    <v-text-field
      slot="activator"
      :label="label"
      v-model="date"
      prepend-icon="event"
      readonly
      required
    ></v-text-field>
    <v-date-picker :allowed-dates="isDateAllowed" v-model="date" no-title scrollable>
      <v-spacer></v-spacer>
      <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
      <v-btn flat color="primary" @click="saveDate(date)">OK</v-btn>
    </v-date-picker>
  </v-menu>
</template>


<script>
  export default {
    name: 'DatePicker',
    props: ['label', 'startDate'],
    data: function() {
      return {
        menu: false,
        date: ''
      }
    },
    methods: {
      saveDate: function (date) {
        this.$refs.menu.save(this.date);
        this.$emit('selectedDate', date)
      },
      isDateAllowed: function (val) {
        return new Date(val) - new Date(this.startDate) >=0;
      }
    }
  }
</script>
