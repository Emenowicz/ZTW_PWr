<template xmlns="">
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
                      :rules="[rules.required, rules.tournamentNameRules]"
                      :counter="50"
                      required/>
                    <v-text-field
                      label="Description"
                      multi-line
                      v-model="description"/>
                    <v-layout row wrap>
                      <v-flex xs12 sm12 md5 lg5>
                        <date-picker :valid="isStartEndDateValid" :startDate="''"
                                     :endDate="(endDate === '') ? '' : endDate"
                                     :label="'Start date'" @selectedDate="onStartDateSelected"
                                     :date="this.startDate"/>
                      </v-flex>
                      <v-spacer/>
                      <v-flex xs12 sm12 md5 lg5>
                        <date-picker :valid="isStartEndDateValid"
                                     :startDate="(startDate === '') ? '' : startDate"
                                     :endDate="''" :label="'End date'" @selectedDate="onEndDateSelected"
                                     :date="this.endDate"/>
                      </v-flex>
                    </v-layout>
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
                    :rules="[rules.required]"
                    required
                  ></v-select>
                  <v-text-field
                    v-if="tournamentType==='Local'"
                    label="Location"
                    v-model="tournamentLocation"
                    :rules="[rules.required]"
                    required
                  ></v-text-field>
                  <v-select
                    label="Mode"
                    v-model="tournamentMode"
                    :items="tournamentModes"
                    :rules="[rules.required]"
                    required
                  ></v-select>
                  <v-layout row wrap>
                    <v-flex xs12 sm12 md5 lg5>
                      <v-text-field
                        label="Min. teams"
                        v-model="minTeams"
                        type="number"
                        min="2"
                        :rules="[isMinMaxTeamsValid]"
                      ></v-text-field>
                    </v-flex>
                    <v-spacer/>
                    <v-flex xs12 sm12 md5 lg5>
                      <v-text-field
                        label="Max. teams"
                        v-model="maxTeams"
                        type="number"
                        :rules="[isMinMaxTeamsValid]"
                      ></v-text-field>
                    </v-flex>
                  </v-layout>
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
      @click="onEditTournament"
      :disabled="!isValid">
      submit
    </v-btn>
  </v-container>
</template>

<script>
  import {mapActions, mapGetters} from 'vuex';
  import DatePicker from '@/components/tools/DatePicker'

  export default {
    name: 'CreateTournament',
    data: function () {
      return {
        valid: false,
        errors: [],
        tournamentName: '',
        description: '',
        tournamentLocation: '',
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
        endDate: '',
        minTeams: '',
        maxTeams: '',
        rules: {
          required: v => !!v || 'Tournament name is required',
          tournamentNameRules: v => (v && v.length <= 50) || 'Name must be less than 50 characters'
        }
      }
    },
    computed: {
      isValid: function () {
        return this.valid && this.isStartEndDateValid;
      },
      isMinMaxTeamsValid: function () {
        return this.maxTeams === '' || this.minTeams === '' || this.minTeams <= this.maxTeams || "Wrong value..";
      },
      isStartEndDateValid: function () {
        return this.startDate === '' || this.endDate === '' || new Date(this.startDate) - new Date(this.endDate) < 0 || "Wrong date."
      },
      ...mapGetters([
        'userId',
        'editedTournament'
      ])
    },
    methods: {
      onStartDateSelected(date) {
        this.startDate = date;
      },
      onEndDateSelected(date) {
        this.endDate = date;
      },
      onEditTournament() {
        if (this.$refs.formBasicInfo.validate() && this.$refs.formGameInfo.validate() && this.isValid) {
          const tournament = {
            name: this.tournamentName,
            startTime: this.startDate,
            endTime: this.endDate,
            minTeams: this.minTeams,
            maxTeams: this.maxTeams
          };
          console.log(tournament);
          this.EDIT_TOURNAMENT(this.userId, tournament).then((response) => {
            console.log(response);
          }, (error) => {
            console.log(error);
          })
        }
      },
      ...mapActions([
        'EDIT_TOURNAMENT'
      ])
    },
    components: {
      'date-picker': DatePicker
    },
    mounted() {
      this.tournamentName = this.editedTournament.name;
      this.description = this.editedTournament.description;
      this.startDate = this.editedTournament.startTime;
      this.endDate = this.editedTournament.endTime;
      this.minTeams = this.editedTournament.minTeams;
      this.maxTeams = this.editedTournament.maxTeams;
      this.tournamentMode = (this.editedTournament.playMode === 'ONEVSONE') ? '1 vs. 1' : '2 vs. 2';
      this.tournamentType = (this.editedTournament.tournamentType === 'LOCAL') ? 'Local' : 'Virtual';
      this.tournamentLocation = this.editedTournament.location;
    }
  }
</script>

<style scoped>


</style>
