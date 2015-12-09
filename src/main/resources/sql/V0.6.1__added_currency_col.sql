ALTER TABLE samples
  ADD currency varchar(4) NOT NULL default 'USD';
ALTER TABLE snapshot
  ADD currency varchar(4) NOT NULL default 'USD';