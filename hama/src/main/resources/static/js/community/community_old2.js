const ajax = {

        get: async url => {
          const option = {
            method: 'GET',
            headers: {
              Accept: 'application/json',
            },
          };
        },

        patch: async (url, datas) => {
            const option = {
              method: 'PATCH',
              headers: {
                'Content-Type': 'application/json',
                Accept: 'application/json',
              },
              body: JSON.stringify(datas),
            };
          },
}

export { ajax };
