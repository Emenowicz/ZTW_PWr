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
      :rules="[isValid]"
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
    props: ['label', 'startDate', 'endDate', 'valid'],
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
        var allowed = false;
        if (this.startDate !== '') {
          allowed = new Date(val) - new Date(this.startDate) >= 0
        }
        if (allowed && this.endDate !== '') {
          allowed = new Date(val) - new Date(this.endDate) <= 0
        }
        return allowed;
      }
    },
    computed: {
      isValid: function () {
        return this.valid || "Wrong date."
      }
    }
  }
</script>
