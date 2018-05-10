import {API, Path} from '@/api/api_config'

function create(tournament) {
  return new Promise((resolve, reject) => {
    API.post(Path.TOURNAMENT, tournament)
       .then(function (response) {
           resolve(response);
        })
       .catch(function (error) {
           reject(error);
       })
  })
}

function update(tournament) {
  return new Promise((resolve, reject) => {
     API.patch(Path.TOURNAMENT, tournament)
        .then(function (response) {
           resolve(response);
        })
        .catch(function (error) {
           reject(error);
        })
  })
}

function getAll() {
  return new Promise((resolve, reject) => {
    API.get(Path.TOURNAMENT)
       .then((response) => {
         console.log(response);
           resolve(response)
       }).catch((error) => {
            reject(error)
       })
  })
}

function queryPage(query, startDate, endDate, page, size) {
  return new Promise((resolve, reject) => {
      const path = buildQueryPagePath(query, startDate, endDate, page, size);
      API.get(path)
         .then((response) => {
             resolve(response)
         }).catch((error) => {
              reject(error)
         })
  })
}

function buildQueryPagePath(query, startDate, endDate, page, size) {
  let path = Path.TOURNAMENT_QUERY_PAGE
  let paramsNumber = 0;
  if(query !== '') {
    if (paramsNumber !== 0) {
      path += '&';
    }
    path += "q=" + query;
    paramsNumber++;
  }
  if (startDate !== '') {
    if (paramsNumber !== 0) {
      path += '&';
    }
    let date = new Date(startDate);
    path += "sd=" + date.getDate() + '-' + ((date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1))  + '-' + date.getFullYear();
    paramsNumber++;
  }
  if (endDate !== '') {
    if (paramsNumber !== 0) {
      path += '&';
    }
    path += "ed=" + endDate;
    paramsNumber++;
  }
  if (page !== '') {
    if (paramsNumber !== 0) {
      path += '&';
    }
    path += "page=" + page;
    paramsNumber++;
  }
  if (size !== '') {
    if (paramsNumber !== 0) {
      path += '&';
    }
    path += "size=" + size;
    paramsNumber++;
  }
  return path;
}

export default {create, update, getAll, queryPage}
