<template>
  <v-container>
    <v-layout row wrap>
      <v-flex xs12 sm12 md8 lg8>
        <v-card dark color="blue-grey darken-2" class="white--text">
          <v-card-title primary-title>
            <div class="title">BASIC INFO</div>
          </v-card-title>
          <v-container grid-list-md text-xs-center>
            <v-layout row wrap>
              <v-flex offset-xs1 xs10 sm10 md10 lg10>
                <template>
                  <v-form dark v-model="valid" ref="formBasicInfo" lazy-validation>
                    <v-text-field
                      label="Tournament name"
                      v-model="tournamentName"
                      :rules="tournamentNameRules"
                      :counter="50"
                      required/>
                    <v-text-field
                      label="Description"
                      multi-line
                      v-model="description"/>

                    <date-picker :startDate=Date.now() :label="'Start date'" @selectedDate="onStartDateSelected"/>

                    <date-picker :startDate="(startDate === '') ? Date.now() : startDate" :label="'End date'"
                                 @selectedDate="onEndDateSelected"/>

                    <v-checkbox
                      label="Do you agree?"
                      v-model="checkbox"
                      :rules="[v => !!v || 'You must agree to continue!']"
                      required
                    ></v-checkbox>
                  </v-form>
                </template>
              </v-flex>
              <v-divider/>
            </v-layout>
          </v-container>

        </v-card>
      </v-flex>
      <v-flex xs12 sm12 md4 lg4>
        <v-card dark color="blue-grey darken-2" class="white--text">
          <v-card-title primary-title>
            <div class="title">GAME SETTINGS</div>
          </v-card-title>
          <v-container grid-list-md text-xs-center>
            <v-layout row wrap>
              <v-flex offset-xs1 xs10 md10 lg10>
                <v-form dark v-model="valid" ref="formGameInfo" lazy-validation>
                  <v-select
                    label="Type"
                    v-model="tournamentType"
                    :items="tournamentTypes"
                    :rules="[v => !!v || 'Item is required']"
                    required
                  ></v-select>
                  <v-text-field
                    v-if="tournamentType==='Local'"
                    label="Location"
                    v-model="tournamentLocation"
                    :rules="tournamentLocationRules"
                    required
                  ></v-text-field>
                  <v-select
                    label="Mode"
                    v-model="tournamentMode"
                    :items="tournamentModes"
                    :rules="[v => !!v || 'Item is required']"
                    required
                  ></v-select>
                </v-form>
              </v-flex>
              <v-divider/>
            </v-layout>
          </v-container>

        </v-card>
      </v-flex>
    </v-layout>
    <v-btn
      fixed bottom
      @click="onCreateNewTournament"
      :disabled="!valid">
      submit
    </v-btn>
  </v-container>
</template>

<script>
  import DatePicker from '@/components/tools/DatePicker'

  export default {
    name: 'CreateTournament',
    data: function () {
      return {
        valid: true,
        tournamentName: '',
        tournamentNameRules: [
          v => !!v || 'Tournament name is required',
          v => (v && v.length <= 50) || 'Name must be less than 50 characters'
        ],
        description: '',
        tournamentLocation: '',
        tournamentLocationRules: [
          v => !!v || 'Tournament name is required'
        ],
        checkbox: false,
        tournamentMode: '',
        tournamentModes: [
          '1 vs. 1',
          '2 vs. 2'
        ],
        tournamentType: '',
        tournamentTypes: [
          'Local',
          'Virtual'
        ],
        startDate: '',
        startDateMenu: false,
        endDate: '',
        endDateMenu: false
      }
    },

    methods: {
      onStartDateSelected(date) {
        if(this.endDate === '' || new Date(date) - new Date(this.endDate) < 0) {
          this.startDate = date;
        }
      },
      onEndDateSelected(date) {
        this.endDate = date;
      },
      onCreateNewTournament() {
        if (this.$refs.formBasicInfo.validate() && this.$refs.formGameInfo.validate()) {

        }
      }
    },
    components: {
      'date-picker': DatePicker
    }
  }
</script>

<style scoped>


</style>
