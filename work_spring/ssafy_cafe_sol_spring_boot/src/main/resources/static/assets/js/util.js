function longToDate(millis) {
  const date = new Date(parseInt(millis));

  const yyyy = date.getFullYear().toString().padStart(2, 0);
  const mm = (date.getMonth() + 1).toString().padStart(2, 0);
  const dd = date.getDate().toString().padStart(2, 0);
  const hh = date.getHours().toString().padStart(2, 0);
  const MM = date.getMinutes().toString().padStart(2, 0);
  const fmt = `${yyyy}.${mm}.${dd} ${hh}:${MM}`;
  
  // const fmt = new Intl.DateTimeFormat("en", {
  //   dateStyle: "short",
  //   timeStyle: "short"
  // }).format(date);

  return fmt;
}

function comma(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}