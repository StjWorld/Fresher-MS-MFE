import Routes from './Route';
export default function Root(props) {
  return <div className='container mx-auto grid grid-cols-6 gamp-4 mt-4'>
  <div className='col-span-5'>
      <Routes/>
  </div>
</div>;
}
